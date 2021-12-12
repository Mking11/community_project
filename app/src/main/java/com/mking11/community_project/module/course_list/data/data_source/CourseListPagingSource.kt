package com.mking11.community_project.module.course_list.data.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto
import com.mking11.community_project.module.course_list.data.repository.CourseListRepository
import com.mking11.community_project.module.course_list.domain.model.CourseListDto
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class CourseListPagingSource(
    private val pageSize: Int,
    private val category: String? = null,
    private val subCategory: String? = null,
    private val search:String?=null,
    private val price: String = "price-free",
    private val language: String = "en",
    private val courseListRepository: CourseListRepository
) : PagingSource<Int, CourseDetailsDto>() {
    private val COURSE_LIST_STARTING_PAGE_INDEX = 1
    override fun getRefreshKey(state: PagingState<Int, CourseDetailsDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CourseDetailsDto> {
        return try {
            val page = params.key ?: COURSE_LIST_STARTING_PAGE_INDEX
            val response: CourseListDto? =
                courseListRepository.getCourseListRemoteResponse(
                    page,
                    pageSize,
                    category,
                    subCategory,
                    search,
                    price,
                    language
                )

            if ( response != null) {
                val courses: List<CourseDetailsDto> = response.results


                val isAtEnd: Boolean = (page + pageSize) == response!!.count
                val nextKey = if (response!!.results.isNullOrEmpty() || isAtEnd) {
                    null
                } else {
                    // initial load size = 3 * NETWORK_PAGE_SIZE
                    // ensure we're not requesting duplicating items, at the 2nd request
                    page + (params.loadSize / pageSize)
                }
                LoadResult.Page(
                    data = courses,
                    prevKey = if (page == COURSE_LIST_STARTING_PAGE_INDEX) null else page - 1,
                    nextKey = page +1
                )
            } else {
                LoadResult.Error(Throwable(message = "error"))
            }


        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}