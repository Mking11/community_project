package com.mking11.community_project.module.course_details.domain.model

import com.mking11.community_project.module.course_details.domain.use_case.GetCoursesDetailsRemote

data class CourseDetailsUseCases(
    val getCoursesDetailsRemote: GetCoursesDetailsRemote
)