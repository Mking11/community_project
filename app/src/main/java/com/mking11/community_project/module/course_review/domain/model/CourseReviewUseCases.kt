package com.mking11.community_project.module.course_review.domain.model

import com.mking11.community_project.module.course_review.domain.use_case.GetCoursePagingSource

data class CourseReviewUseCases(
    val coursePagingSource: GetCoursePagingSource
)