package com.mking11.community_project.module.course_details.data.data_source

import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CourseDetailService {

    //get /api-2.0/courses/433798/

    @GET("/courses/{courseId}")
    fun getCourseDetails(
        @Path(
            value = "courseId",
            encoded = true
        ) courserId: Int
    ): Response<CourseDetailsDto>

}