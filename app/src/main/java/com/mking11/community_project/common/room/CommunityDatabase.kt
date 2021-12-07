package com.mking11.community_project.common.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mking11.community_project.module.course_details.data.data_source.CourseDao
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo

@Database(
    entities = [
        CourseDetailsDbo::class
    ], version = 1, exportSchema = false
)
abstract class CommunityDatabase : RoomDatabase() {

    abstract val courseDao: CourseDao

    companion object {
        val DataBaseName: String = "CommunityDatabase"
    }
}