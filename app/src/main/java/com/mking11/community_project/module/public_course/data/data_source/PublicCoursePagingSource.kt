package com.mking11.community_project.module.public_course.data.data_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mking11.community_project.module.public_course.data.repository.CoursePublicCurriculumRepository
import com.mking11.community_project.module.public_course.domain.model.PublicCourseList
import com.mking11.community_project.module.public_course.domain.model.PublicCourseResult
import retrofit2.HttpException
import java.io.IOException

class PublicCoursePagingSource(
    private val pageSize: Int,
    private val courseId: Int,
    private val coursePublicCurriculumRepository: CoursePublicCurriculumRepository
) : PagingSource<Int, PublicCourseResult>() {

    private val publicCourseResultStartIndex = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PublicCourseResult> {
        return try {
            val page = params.key ?: publicCourseResultStartIndex

            val response: PublicCourseList =
                coursePublicCurriculumRepository.getCourseCurriculum(courseId, page, pageSize)

            val chapters = response.results

            val isAtEnd: Boolean = (page + pageSize) == response.count

            LoadResult.Page(
                data = chapters,
                prevKey = if (page == publicCourseResultStartIndex) null else page - 1,
                nextKey = page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PublicCourseResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}