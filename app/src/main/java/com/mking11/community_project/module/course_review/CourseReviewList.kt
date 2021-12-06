package com.mking11.community_project.module.course_review

data class CourseReviewList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)