package com.mking11.community_project.module.course_review.data.data_source

import com.mking11.community_project.module.course_review.domain.model.CourseReviewList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CourseReviewService {


    @GET("courses/{courseId}/reviews")
    suspend fun getCourseReviews(
        @Path(
            value = "courseId",
            encoded = true
        ) courserId: Int,
        @Query("page") page: Int?,
        @Query("page_size") pageSize: Int?,
    ): CourseReviewList

}