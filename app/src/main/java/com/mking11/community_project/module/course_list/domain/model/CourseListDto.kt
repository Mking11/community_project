package com.mking11.community_project.module.course_list.domain.model

import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto

data class CourseListDto (
    val count : Int,
    val next : String,
    val previous : String,
    val results : List<CourseDetailsDto>,
    val search_tracking_id : String
)