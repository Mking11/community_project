package com.mking11.community_project.module.course_list.domain.use_case

import com.mking11.community_project.module.course_list.data.data_source.CourseListPagingSource
import com.mking11.community_project.module.course_list.data.repository.CourseListRepository

class GetCoursesRemote(private val courseListRepository: CourseListRepository) {

    operator fun invoke(
        page: Int,
        pageSize: Int,
        category: String?,
        subCategory: String?,
        price: String?,
        language: String?
    ): CourseListPagingSource {
        val result = CourseListPagingSource(
            pageSize,
            category,
            subCategory,
            courseListRepository = courseListRepository
        )
        return result
    }
}