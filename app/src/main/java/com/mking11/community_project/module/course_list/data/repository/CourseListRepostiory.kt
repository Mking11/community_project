package com.mking11.community_project.module.course_list.data.repository

import androidx.paging.PagingSource
import com.mking11.community_project.common.utils.AppResult
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
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