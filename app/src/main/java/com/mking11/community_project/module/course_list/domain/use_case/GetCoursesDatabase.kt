package com.mking11.community_project.module.course_list.domain.use_case

import androidx.paging.PagingSource
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
import com.mking11.community_project.module.course_details.domain.model.ICourseDetails
import com.mking11.community_project.module.course_list.data.repository.CourseListRepository

class GetCoursesDatabase(private val courseListRepository: CourseListRepository) {


    operator fun invoke(search:String?,subcategory:String?): PagingSource<Int, CourseDetailsDbo> {
        return courseListRepository.getCourses(search, subcategory)
    }

}