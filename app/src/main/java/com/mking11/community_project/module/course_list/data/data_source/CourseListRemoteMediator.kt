package com.mking11.community_project.module.course_list.data.data_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto
import com.mking11.community_project.module.course_list.data.repository.CourseListRepository
import com.mking11.community_project.module.course_list.domain.model.CourseListDto
import com.mking11.community_project.module.course_list.domain.model.CourseRemoteIndexDbo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


@ExperimentalPagingApi
class CourseListRemoteMediator(
    private val pageSize: Int,
    private val category: String? = null,
    private val subCategory: String? = null,
    private val search: String? = null,
    private val price: String = "price-free",
    private val language: String = "en",
    private val courseListRepository: CourseListRepository
) : RemoteMediator<Int, CourseDetailsDbo>() {

    companion object {
        const val COURSE_LIST_STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CourseDetailsDbo>
    ): MediatorResult {
        val page: Int? = when (loadType) {


            LoadType.REFRESH -> null
            LoadType.PREPEND ->{
                    return MediatorResult.Success(endOfPaginationReached = true )

            }
            LoadType.APPEND -> {

                val courseRemoteIndex = getCourseRemoteIndexForLastItem(state)
                println("append last index ${courseRemoteIndex?.nextKey}  ${courseRemoteIndex?.prevKey}")


                val nextKey = courseRemoteIndex?.nextKey

                if (nextKey == null) {
                    MediatorResult.Success(endOfPaginationReached = courseRemoteIndex != null)
                }

                nextKey
            }
        }

        try {
            println("page $page")


            val response: CourseListDto? = page?.let {
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



            println("response $response")


            val courseSize: Int? = (response?.count?.div(pageSize))
            println("response BODY $response")
            println("count size $courseSize")
            val courses: List<CourseDetailsDto>? = response?.results
            val isAtEnd: Boolean = response?.results?.isEmpty() == true
            println("is at an end $isAtEnd")


            val prevKey = if (page == COURSE_LIST_STARTING_PAGE_INDEX) null else page?.minus(1)
            val nextKey = if (isAtEnd) null else page?.plus(1)

            CoroutineScope(Dispatchers.IO).launch {

                if (loadType == LoadType.REFRESH) {
                    println("clearing tables")
                    courseListRepository.clearKeys()
                    courseListRepository.clearData()

                }
                val courseListIndex = courses?.mapNotNull {
                    it.id?.let { it1 ->
                        CourseRemoteIndexDbo(
                            it1,
                            prevKey = prevKey,
                            nextKey = nextKey
                        )
                    }
                }

                println("inserting courses $courses  courseIndex $courseListIndex")
                if (courseListIndex != null) {
                    courseListRepository.insertCourseListIndex(courseListIndex)
                }
                if (courses != null) {

                    courseListRepository.insertList(courses)
                }

            }




            return MediatorResult.Success(endOfPaginationReached = isAtEnd)


        } catch (exception: IOException) {
            println("exception $exception")
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            println("exception $exception")
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getCourseRemoteIndexForLastItem(state: PagingState<Int, CourseDetailsDbo>): CourseRemoteIndexDbo? {


        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { repo ->
            println("fetch course Id $repo.id")
            repo.id?.let { courseListRepository.getCourseListIndex(it) }
        }
    }


    private suspend fun getCourseRemoteIndexForFirstItem(state: PagingState<Int, CourseDetailsDbo>): CourseRemoteIndexDbo? {

        println("get course remote index for first item ${state.anchorPosition}")
        return state.pages.firstOrNull() { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { course ->
                course.id?.let {
                    println("fetch course Id $it")
                    courseListRepository.getCourseListIndex(it)
                }

            }


    }

    private suspend fun getCourseRemoteIndexCurrentPosition(
        state: PagingState<Int, CourseDetailsDbo>
    ): CourseRemoteIndexDbo? {
        println("refresh index current positon ")
        return state.anchorPosition?.let { position ->
            println("get course $position")
            state.closestItemToPosition(position)?.id?.let {
                println("fetch course Id $it")
                courseListRepository.getCourseListIndex(it)
            }
        }
    }


    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }
}