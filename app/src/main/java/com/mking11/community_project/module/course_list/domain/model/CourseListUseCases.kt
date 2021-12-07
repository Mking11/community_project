package com.mking11.community_project.module.course_list.domain.model

import com.mking11.community_project.module.course_list.domain.use_case.GetCoursesRemote
import com.mking11.community_project.module.course_list.domain.use_case.InsertCourses

data class CourseListUseCases(
    val getCoursesRemote: GetCoursesRemote,
    val insertCourses: InsertCourses
)