package com.mking11.community_project.module.public_course.data.repository

import com.mking11.community_project.module.public_course.data.data_source.PublicCourseService
import com.mking11.community_project.module.public_course.domain.model.PublicCourseList

class CoursePublicCurriculumRepositoryImpl(
    private val courseService: PublicCourseService
) : CoursePublicCurriculumRepository {
    override suspend fun getCourseCurriculum(courseId: Int, page: Int, pageSize: Int): PublicCourseList {

        return courseService.getCoursePublicCurriculum(courseId,page,pageSize)

    }
}