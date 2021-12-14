package com.mking11.community_project.module.course_review.data.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mking11.community_project.module.course_review.data.repository.CourseReviewRepository
import com.mking11.community_project.module.course_review.domain.model.CourseReviewList
import com.mking11.community_project.module.course_review.domain.model.Result
import retrofit2.HttpException
import java.io.IOException

class CourseReviewPagingSource(
    private val pageSize: Int,
    private val courseId: Int,
    private val courseReviewRepository: CourseReviewRepository
) : PagingSource<Int, Result>() {
    private val courseReviewStartIndex = 1
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val page = params.key ?: courseReviewStartIndex
            val response: CourseReviewList =
                courseReviewRepository.getCourseReviewService(
                    courseId, page, pageSize
                )


            val reviews: List<Result> = response.results


            val isAtEnd: Boolean = (page + pageSize) == response.count
            val nextKey = if (response.results.isNullOrEmpty() || isAtEnd) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                page + (params.loadSize / pageSize)
            }
            LoadResult.Page(
                data = reviews,
                prevKey = if (page == courseReviewStartIndex) null else page - 1,
                nextKey = page + 1
            )


        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}