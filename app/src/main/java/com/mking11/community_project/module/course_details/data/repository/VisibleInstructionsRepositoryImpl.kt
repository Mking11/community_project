package com.mking11.community_project.module.course_details.data.repository

import com.mking11.community_project.common.room.room_helper.ScopeShared
import com.mking11.community_project.common.room.room_helper.repositories.DaoEssentialsRepositoryImpl
import com.mking11.community_project.module.course_details.data.data_source.VisibleInstructorsDao
import com.mking11.community_project.module.course_details.domain.model.VisibleInstructorsDbo
import com.mking11.community_project.module.course_details.domain.model.VisibleInstructorsDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class VisibleInstructionsRepositoryImpl(
    private val visibleInstructorsDao: VisibleInstructorsDao
) : VisibleInstructionsRepository,
    DaoEssentialsRepositoryImpl<VisibleInstructorsDbo> {

    private val scope = ScopeShared(this.javaClass, CoroutineScope(Dispatchers.IO))

    override fun getInstructors(courseId: Int): Flow<List<VisibleInstructorsDbo>> {
        return visibleInstructorsDao.getVisibleInstructors(courseId)
    }

    override fun insertAll(instructorPair: List<Pair<List<VisibleInstructorsDto>?, Int?>>) {
        instructorPair.forEach {
            if (it.second != null) {
                it.first?.forEach { visibleInstructor ->
                    super.insertOrUpdate(
                        visibleInstructor.toDbo(it.second!!),
                        scope.scope,
                        scope.handler,
                        visibleInstructorsDao
                    )
                }
            }

        }
    }

    override suspend fun clearTable() {
        visibleInstructorsDao.clearVisibleInstructors()
    }
}