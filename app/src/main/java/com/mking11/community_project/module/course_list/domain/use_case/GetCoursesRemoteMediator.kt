package com.mking11.community_project.module.course_list.domain.use_case

import androidx.paging.ExperimentalPagingApi
import com.mking11.community_project.common.room.CommunityDatabase
import com.mking11.community_project.module.course_list.data.data_source.CourseListRemoteMediator
import com.mking11.community_project.module.course_list.data.repository.CourseListRepository

class GetCoursesRemoteMediator(
    private val courseListRepository: CourseListRepository,
    private val database: CommunityDatabase
) {

    @ExperimentalPagingApi
    operator fun invoke(
        pageSize: Int,
        category: String?,
        subCategory: String?,
        search: String?,
        language: String = "en"
    ): CourseListRemoteMediator {
        return CourseListRemoteMediator(
            pageSize = pageSize,
            category = category,
            subCategory = subCategory,
            search = search,
            language = language,
            courseListRepository = courseListRepository

        )
    }
}