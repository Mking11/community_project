package com.mking11.community_project.module.course_review.data.repository

import com.mking11.community_project.module.course_review.data.data_source.CourseReviewService
import com.mking11.community_project.module.course_review.domain.model.CourseReviewList

class CourseReviewRepositoryImpl(
    private val courseReviewService: CourseReviewService
) : CourseReviewRepository {
    override suspend fun getCourseReviewService(
        courseId: Int,
        page: Int,
        page_size: Int
    ): CourseReviewList {
        return courseReviewService.getCourseReviews(courseId, page, page_size)
    }
}