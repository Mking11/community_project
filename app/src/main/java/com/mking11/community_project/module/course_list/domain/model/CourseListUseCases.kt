package com.mking11.community_project.module.course_list.domain.model

import com.mking11.community_project.module.course_list.domain.use_case.GetCourseDatabaseSearch
import com.mking11.community_project.module.course_list.domain.use_case.GetCoursesDatabase
import com.mking11.community_project.module.course_list.domain.use_case.GetCoursesRemoteMediator
import com.mking11.community_project.module.course_list.domain.use_case.InsertCourses

data class CourseListUseCases(
    val getCoursesRemoteMediator: GetCoursesRemoteMediator,
    val getCoursesDatabase: GetCoursesDatabase,
    val insertCourses: InsertCourses,
    val getCourseDatabaseSearch: GetCourseDatabaseSearch
)