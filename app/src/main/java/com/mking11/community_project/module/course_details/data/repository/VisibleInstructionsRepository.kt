package com.mking11.community_project.module.course_details.data.repository

import com.mking11.community_project.module.course_details.domain.model.VisibleInstructorsDbo
import com.mking11.community_project.module.course_details.domain.model.VisibleInstructorsDto
import kotlinx.coroutines.flow.Flow

interface VisibleInstructionsRepository {

    fun getInstructors(courseId: Int): Flow<List<VisibleInstructorsDbo>>
    fun insertAll(instructorPair: List<Pair<List<VisibleInstructorsDto>?, Int?>>)
    suspend fun clearTable()
}