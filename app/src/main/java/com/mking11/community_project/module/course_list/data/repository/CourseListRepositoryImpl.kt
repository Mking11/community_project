package com.mking11.community_project.module.course_list.data.repository

import com.mking11.community_project.common.api.domain.utils.apiCall
import com.mking11.community_project.common.utils.AppResult
import com.mking11.community_project.module.course_details.data.repository.CourseRepository
import com.mking11.community_project.module.course_list.data.data_source.CourseListService
import com.mking11.community_project.module.course_list.domain.model.CourseList
import kotlinx.coroutines.flow.Flow

class CourseListRepositoryImpl(
    private val services: CourseListService,
    private val courseRepository: CourseRepository
) : CourseListRepostiory {
    override fun getCourseListRemote(
        page: Int,
        pageSize: Int,
        category: String?,
        subCategory: String?,
        price: String?,
        language: String?
    ): Flow<AppResult<CourseList>> {
        return apiCall {
            services.getCourseList(page, pageSize, category, subCategory, price, language)
        }

    }

    override fun insertCoursesToDb(courseCourseList: CourseList) {
        courseCourseList.results.forEach {
            courseRepository.insertCourse(it)
        }
    }


}