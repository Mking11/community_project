package com.mking11.community_project.module.course_list.data.data_source;

import androidx.room.*
import com.mking11.community_project.common.room.room_helper.contracts.IDaoEssentialsRepository
import com.mking11.community_project.module.course_list.domain.model.CourseRemoteIndexDbo

@Dao
abstract class CourseIndexDao : IDaoEssentialsRepository<CourseRemoteIndexDbo, String> {

    @Query("Select * from CourseIndex where id=:id")
    abstract suspend fun getCourseList(id:Int):CourseRemoteIndexDbo?


    @Query("Delete from CourseIndex")
    abstract suspend fun clearCourseIndex()
}
