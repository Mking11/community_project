package com.mking11.community_project.module.public_course.data.repository

import com.mking11.community_project.module.course_list.presentation.CourseList
import com.mking11.community_project.module.public_course.domain.model.PublicCourseList

interface CoursePublicCurriculumRepository {

    suspend fun getCourseCurriculum(
        courseId: Int,
        page: Int,
        page_size: Int
    ): PublicCourseList
}