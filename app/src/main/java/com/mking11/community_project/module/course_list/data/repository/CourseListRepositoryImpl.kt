package com.mking11.community_project.module.course_list.data.repository

import com.mking11.community_project.common.api.domain.utils.apiCall
import com.mking11.community_project.common.utils.AppResult
import com.mking11.community_project.module.course_details.data.repository.CourseRepository
import com.mking11.community_project.module.course_list.data.data_source.CourseListService
import com.mking11.community_project.module.course_list.domain.model.CourseListDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class CourseListRepositoryImpl(
    private val services: CourseListService,
    private val courseRepository: CourseRepository
) : CourseListRepository {
    override fun getCourseListRemote(
        page: Int,
        pageSize: Int,
        category: String?,
        subCategory: String?,
        price: String?,
        language: String?
    ): Flow<AppResult<CourseListDto>> {
        return apiCall {
            services.getCourseList(page, pageSize, category, subCategory, price, language)
        }

    }

    override suspend fun getCourseListRemoteResponse(
        page: Int,
        pageSize: Int,
        category: String?,
        subCategory: String?,
        price: String?,
        language: String?
    ): Response<CourseListDto> {
         return  services.getCourseList(page, pageSize, category, subCategory, price, language)
    }

    override fun insertCoursesToDb(courseCourseListDto: CourseListDto) {
        courseCourseListDto.results.forEach {
            courseRepository.insertCourse(it)
        }
    }


}