package com.mking11.community_project.module.course_review.data.repository

import com.mking11.community_project.module.course_review.domain.model.CourseReviewList

interface CourseReviewRepository {
    suspend fun getCourseReviewService(
        courseId:Int,page:Int,page_size:Int
    ): CourseReviewList
}