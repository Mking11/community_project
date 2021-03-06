package com.mking11.community_project.module.course_details.data.repository

import androidx.paging.PagingSource
import com.mking11.community_project.common.utils.AppResult
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto
import com.mking11.community_project.module.course_details.domain.model.ICourseDetails
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
     fun insertCourse(courseDetailsDto: CourseDetailsDto,subcategory:String?,search: String?)
    fun getCoursePaging(search: String?,subcategory: String?): PagingSource<Int, CourseDetailsDbo>
    fun getCoursePaging(search:String): PagingSource<Int, CourseDetailsDbo>
    suspend fun clearCourseTable()

}