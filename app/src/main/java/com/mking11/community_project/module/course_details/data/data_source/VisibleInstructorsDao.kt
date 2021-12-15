package com.mking11.community_project.module.course_details.data.data_source;

import androidx.room.Dao
import androidx.room.Query
import com.mking11.community_project.common.room.room_helper.contracts.IDaoEssentialsRepository
import com.mking11.community_project.module.course_details.domain.model.VisibleInstructorsDbo
import kotlinx.coroutines.flow.Flow

@Dao
abstract class VisibleInstructorsDao : IDaoEssentialsRepository<VisibleInstructorsDbo> {

    @Query("Select * from VisibleInstructors where courseId=:id order by display_name")
    abstract fun getVisibleInstructors(
        id: Int
    ): Flow<List<VisibleInstructorsDbo>>


    @Query("Delete from VisibleInstructors")
    abstract fun clearVisibleInstructors()
}