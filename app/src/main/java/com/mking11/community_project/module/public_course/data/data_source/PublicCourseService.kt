package com.mking11.community_project.module.public_course.data.data_source

import com.mking11.community_project.module.public_course.domain.model.PublicCourseList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PublicCourseService {

    @GET("courses/{courseId}/public-curriculum-items")
    suspend fun getCoursePublicCurriculum(
        @Path(
            value = "courseId",
            encoded = true
        ) courserId: Int,
        @Query("page") page: Int?,
        @Query("page_size") pageSize: Int?,
    ): PublicCourseList
}