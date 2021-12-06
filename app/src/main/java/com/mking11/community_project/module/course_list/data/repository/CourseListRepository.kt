package com.mking11.community_project.module.course_list.data.repository

import com.mking11.community_project.common.utils.AppResult
import com.mking11.community_project.module.course_list.domain.model.CourseListDto
import kotlinx.coroutines.flow.Flow

interface CourseListRepository {
    fun getCourseListRemote(
        page: Int,
        pageSize: Int,
        category: String?,
        subCategory: String?,
        price: String?,
        language: String?
    ): Flow<AppResult<CourseListDto>>

    fun insertCoursesToDb(
        courseCourseListDto: CourseListDto
    )

}