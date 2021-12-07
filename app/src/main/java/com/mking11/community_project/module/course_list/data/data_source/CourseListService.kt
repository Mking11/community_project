package com.mking11.community_project.module.course_list.data.data_source

import com.mking11.community_project.module.course_list.domain.model.CourseListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CourseListService {
//    get /api-2.0/courses/?page=1&page_size=20&category=Development&subcategory=Mobile%20Development&price=price-free&language=en

    @GET("courses")
    suspend fun getCourseList(
        @Query("page") page: Int?,
        @Query("page_size") pageSize: Int?,
        @Query("category") category: String?,
        @Query("subcategory") subCategory: String?,
        @Query("price") price: String?,
        @Query("language") language: String? = "en"
    ): Response<CourseListDto>
}