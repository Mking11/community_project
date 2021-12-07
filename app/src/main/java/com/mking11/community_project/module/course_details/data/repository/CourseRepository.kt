package com.mking11.community_project.module.course_details.data.repository

import androidx.paging.PagingSource
import com.mking11.community_project.common.utils.AppResult
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    suspend fun insertCourse(courseDetailsDto: CourseDetailsDto)

    fun getCoursePaging(): PagingSource<Int, CourseDetailsDbo>
    fun getCourseDetails(id:Int): Flow<AppResult<CourseDetailsDto>>
    suspend fun clearCourseTable()

}