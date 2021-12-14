package com.mking11.community_project.module.course_review.domain.model

data class Result(
    val _class: String,
    val content: String,
    val created: String,
    val id: Int,
    val modified: String,
    val rating: Double,
    val user: User,
    val user_modified: String
)