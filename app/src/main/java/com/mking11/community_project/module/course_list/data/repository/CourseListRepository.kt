package com.mking11.community_project.module.course_list.data.repository

import androidx.paging.PagingSource
import com.mking11.community_project.common.utils.AppResult
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto
import com.mking11.community_project.module.course_details.domain.model.ICourseDetails
import com.mking11.community_project.module.course_list.domain.model.CourseListDto
import com.mking11.community_project.module.course_list.domain.model.CourseRemoteIndexDbo
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

    fun insertCourseListIndex(list: List<CourseRemoteIndexDbo>)

     fun insertCourseList(courseRemoteIndexDbo: CourseRemoteIndexDbo)

    fun getCourseBySearch(title:String): PagingSource<Int, CourseDetailsDbo>

    fun getCourses(search: String?,subcategory:String?):PagingSource<Int,CourseDetailsDbo>



    suspend fun getCourseListRemoteResponse(
        page: Int,
        pageSize: Int,
        category: String?,
        subCategory: String?,
        search: String?,
        price: String?,
        language: String?
    ): CourseListDto

    fun insertList(list: List<CourseDetailsDto>,search: String?,subCategory: String?)
    suspend fun clearData()
    suspend fun clearKeys()

}