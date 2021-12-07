package com.mking11.community_project.module.course_list.data.repository

import com.mking11.community_project.common.utils.AppResult
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto
import com.mking11.community_project.module.course_list.domain.model.CourseRemoteIndexDbo
import com.mking11.community_project.module.course_list.domain.model.CourseListDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CourseListRepository {
    fun getCourseListRemote(
        page: Int,
        pageSize: Int,
        category: String?,
        subCategory: String?,
        search: String?,
        price: String?,
        language: String?
    ): Flow<AppResult<CourseListDto>>

    suspend fun getCourseListIndex(id: Int): CourseRemoteIndexDbo?

    suspend fun insertCourseListIndex (list :List<CourseRemoteIndexDbo>)

    suspend fun insertCourseList( courseRemoteIndexDbo: CourseRemoteIndexDbo)

    suspend fun getCourseListRemoteResponse(
        page: Int,
        pageSize: Int,
        category: String?,
        subCategory: String?,
        search: String?,
        price: String?,
        language: String?
    ): Response<CourseListDto>

    suspend fun insertList(list: List<CourseDetailsDto>)
    suspend fun  clearTables()

}