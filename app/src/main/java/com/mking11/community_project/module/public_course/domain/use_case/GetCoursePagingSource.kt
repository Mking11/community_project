package com.mking11.community_project.module.public_course.domain.use_case

import com.mking11.community_project.module.public_course.data.data_source.PublicCoursePagingSource
import com.mking11.community_project.module.public_course.data.repository.CoursePublicCurriculumRepository
import com.mking11.community_project.module.public_course.domain.model.PublicCourseList

class GetCoursePagingSource(
    private val coursePublicCurriculumRepository: CoursePublicCurriculumRepository
) {

    operator fun invoke(courseId: Int, pageSize: Int): PublicCoursePagingSource {
        return PublicCoursePagingSource(pageSize, courseId, coursePublicCurriculumRepository)

    }
}