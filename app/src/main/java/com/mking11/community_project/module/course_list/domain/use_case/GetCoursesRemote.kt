package com.mking11.community_project.module.course_list.domain.use_case

import com.mking11.community_project.common.utils.AppResult
import com.mking11.community_project.module.course_list.data.repository.CourseListRepository
import com.mking11.community_project.module.course_list.domain.model.CourseListDto
import kotlinx.coroutines.flow.Flow

class GetCoursesRemote(private val courseListRepository: CourseListRepository) {

    operator fun invoke(
        page: Int,
        pageSize: Int,
        category: String?,
        subCategory: String?,
        price: String?,
        language: String?
    ): Flow<AppResult<CourseListDto>> {
        return courseListRepository.getCourseListRemote(
            page,
            pageSize,
            category,
            subCategory,
            price,
            language
        )
    }
}