package com.mking11.community_project.module.course_review.domain.use_case

import com.mking11.community_project.module.course_review.data.data_source.CourseReviewPagingSource
import com.mking11.community_project.module.course_review.data.repository.CourseReviewRepository

class GetCoursePagingSource(
    private val courseReviewRepository: CourseReviewRepository
) {

    operator fun invoke(courseId: Int, pageSize: Int): CourseReviewPagingSource {
        return CourseReviewPagingSource(pageSize, courseId, courseReviewRepository)
    }
}