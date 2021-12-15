package com.mking11.community_project.module.course_details.domain.model

data class VisibleInstructorsDto(
    val _class: String,
    val title: String,
    val name: String,
    val display_name: String,
    val job_title: String,
    val image_50x50: String,
    val image_100x100: String,
    val initials: String,
    val url: String
) {
    fun toDbo(id: Int): VisibleInstructorsDbo {
        return VisibleInstructorsDbo(
            _class,
            id,
            title,
            name,
            display_name,
            job_title,
            image_50x50,
            image_100x100,
            initials,
            url
        )
    }
}