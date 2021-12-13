package com.mking11.community_project.module.course_list.domain.model

import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo

interface CourseListInteraction {
    fun onCourseItemClicked(courseDetailsDbo: CourseDetailsDbo)
}