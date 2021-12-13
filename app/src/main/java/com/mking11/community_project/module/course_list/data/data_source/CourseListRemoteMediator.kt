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

    var prev: Int? = null
    var next: Int? = null

    companion object {
        const val COURSE_LIST_STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CourseDetailsDbo>
    ): MediatorResult {
        val page: Int? = when (loadType) {


            LoadType.REFRESH -> {
                println("refresh called")
                null
            }
            LoadType.PREPEND -> {
                println("prepend called")
                return MediatorResult.Success(endOfPaginationReached = true)

            }
            LoadType.APPEND -> {

                val nextIndex = getCourseRemoteIndexForLastItem(state)
                println("next called $next")
                if (nextIndex?.nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = true)

                }
                next ?: nextIndex.nextKey
            }
        }

        try {
            println("page $next")


            val response: CourseListDto = (next ?: COURSE_LIST_STARTING_PAGE_INDEX).let {

                println("get ${it}")
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


            next = getIntFromUrl(response.next)
            prev = getIntFromUrl(response.previous)


            val courseSize: Int = (response.count.div(pageSize))
            println("response BODY $response")
            println("count size $courseSize")
            val courses: List<CourseDetailsDto>? = response.results
            val isAtEnd: Boolean = response.results.isEmpty()
            println("is at an end $isAtEnd")


//            val prevKey = if (page == COURSE_LIST_STARTING_PAGE_INDEX) null else page?.minus(1)
//            val nextKey = if (isAtEnd) null else (page ?: COURSE_LIST_STARTING_PAGE_INDEX).plus(1)

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
                            prevKey = prev,
                            nextKey = next
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
            println("exception $exception ")
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            println("exception $exception")
            return MediatorResult.Error(exception)
        }
    }

    private fun getIntFromUrl(url: String?): Int? {


        return url?.split("https://www.udemy.com/api-2.0/courses/?language=en&page=")

            ?.getOrNull(1)?.split("&page_size=")?.getOrNull(0)?.toString()?.toInt()
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