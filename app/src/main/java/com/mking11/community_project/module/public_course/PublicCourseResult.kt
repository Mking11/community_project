package com.mking11.community_project.module.public_course


data class PublicCourseResult(
    val _class: String,
    val id: Int,
    val created: String,
    val sort_order: Int,
    val title: String,
    val description: String,
    val is_published: Boolean
)

