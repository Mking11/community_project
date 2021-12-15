package com.mking11.community_project.module.course_details.domain.use_case

import com.mking11.community_project.module.course_details.data.repository.VisibleInstructionsRepository
import com.mking11.community_project.module.course_details.domain.model.VisibleInstructorsDbo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class GetCoursesDetailsRemote(private val visibleInstructionsRepository: VisibleInstructionsRepository) {
    @ExperimentalCoroutinesApi
    operator fun invoke(courseId: Int): Flow<List<VisibleInstructorsDbo>> {
        return visibleInstructionsRepository.getInstructors(courseId)
    }


}