package com.mking11.community_project.module.course_list.data.repository

import com.mking11.community_project.common.utils.AppResult
import com.mking11.community_project.module.course_list.domain.model.CourseList
import kotlinx.coroutines.flow.Flow

interface CourseListRepostiory {
    fun getCourseListRemote(
        page: Int,
        pageSize: Int,
        category: String?,
        subCategory: String?,
        price: String?,
        language: String?
    ): Flow<AppResult<CourseList>>

    fun insertCoursesToDb(
        courseCourseList: CourseList
    )

}