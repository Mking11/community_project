package com.mking11.community_project.common.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mking11.community_project.module.course_details.data.data_source.CourseDao
import com.mking11.community_project.module.course_details.data.data_source.VisibleInstructorsDao
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
import com.mking11.community_project.module.course_details.domain.model.VisibleInstructorsDbo
import com.mking11.community_project.module.course_list.data.data_source.CourseIndexDao
import com.mking11.community_project.module.course_list.domain.model.CourseRemoteIndexDbo

@Database(
    entities = [
        CourseDetailsDbo::class,
        CourseRemoteIndexDbo::class,
        VisibleInstructorsDbo::class,
    ], version = 1, exportSchema = false
)
abstract class CommunityDatabase : RoomDatabase() {

    abstract val courseDao: CourseDao
    abstract val courseIndexDao: CourseIndexDao
    abstract val visibleInstructorsDao: VisibleInstructorsDao

    companion object {
        val DataBaseName: String = "CommunityDatabase"
    }
}