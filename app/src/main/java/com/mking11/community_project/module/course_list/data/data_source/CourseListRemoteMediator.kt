package com.mking11.community_project.module.course_list.data.data_source

import androidx.paging.*
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto
import com.mking11.community_project.module.course_details.domain.model.ICourseDetails
import com.mking11.community_project.module.course_list.data.repository.CourseListRepository
import com.mking11.community_project.module.course_list.domain.model.CourseListDto
import com.mking11.community_project.module.course_list.domain.model.CourseRemoteIndexDbo
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CourseListRemoteMediator(
    private val pageSize: Int,
    private val category: String? = null,
    private val subCategory: String? = null,
    private val search: String? = null,
    private val price: String = "price-free",
    private val language: String = "en",
    private val courseListRepository: CourseListRepository
) : RemoteMediator<Int, ICourseDetails>() {

    companion object {
        const val COURSE_LIST_STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ICourseDetails>
    ): MediatorResult {
        val page: Int? = when (loadType) {
            LoadType.REFRESH -> {

                val courseRemoteIndex = getCourseRemoteIndexCurrentPosition(state)
                courseRemoteIndex?.nextKey?.minus(1) ?: COURSE_LIST_STARTING_PAGE_INDEX

            }
            LoadType.PREPEND -> {
                val courseRemoteIndex = getCourseRemoteIndexForFirstItem(state)
                val prevKey = courseRemoteIndex?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = courseRemoteIndex != null)

                prevKey
            }
            LoadType.APPEND -> {
                val courseRemoteIndex = getCourseRemoteIndexForLastItem(state)

                val nextKey = courseRemoteIndex?.nextKey

                if (nextKey == null) {
                    MediatorResult.Success(endOfPaginationReached = courseRemoteIndex != null)
                }

                nextKey
            }
        }

        try {
            val response: Response<CourseListDto>? =
                page?.let {
                    courseListRepository.getCourseListRemoteResponse(
                        it,
                        pageSize,
                        category,
                        subCategory,
                        search,
                        price,
                        language
                    )
                }

            if (response != null && !response.isSuccessful) {
                val courses: List<CourseDetailsDto> = response.body()!!.results
                val isAtEnd: Boolean = (page + pageSize) == response.body()!!.count
                if (loadType == LoadType.REFRESH) {
                    courseListRepository.clearTables()
                }

                val prevkey = if (page == COURSE_LIST_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (isAtEnd) null else page + 1
                val courseListIndex = courses.mapNotNull {
                    it.id?.let { it1 -> CourseRemoteIndexDbo(it1, prevkey, nextKey) }
                }

                courseListRepository.insertCourseListIndex(courseListIndex)
                courseListRepository.insertList(courses)
                return MediatorResult.Success(endOfPaginationReached = isAtEnd)
            } else {
                return MediatorResult.Error(Throwable("response was null "))
            }
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getCourseRemoteIndexForLastItem(state: PagingState<Int, ICourseDetails>): CourseRemoteIndexDbo? {

        return state.pages.lastOrNull() {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let {
            it.id?.let { it1 -> courseListRepository.getCourseListIndex(it1) }
        }

    }

    private suspend fun getCourseRemoteIndexForFirstItem(state: PagingState<Int, ICourseDetails>): CourseRemoteIndexDbo? {
        return state.pages.firstOrNull() {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { course ->
            course.id?.let { courseListRepository.getCourseListIndex(it) }

        }


    }

    private suspend fun getCourseRemoteIndexCurrentPosition(
        state: PagingState<Int, ICourseDetails>
    ): CourseRemoteIndexDbo? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {
                courseListRepository.getCourseListIndex(it)
            }
        }
    }


    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }
}