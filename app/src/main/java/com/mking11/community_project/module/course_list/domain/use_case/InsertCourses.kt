package com.mking11.community_project.module.course_list.domain.use_case

import com.mking11.community_project.module.course_details.data.repository.CourseRepository
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto

class InsertCourses(private val courseRepository: CourseRepository) {
     operator fun invoke(courseDetailsDto: CourseDetailsDto) {
        courseRepository.insertCourse(courseDetailsDto = courseDetailsDto)
    }
}