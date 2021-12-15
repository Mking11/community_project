package com.mking11.community_project.module.course_details.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "VisibleInstructors", primaryKeys = ["courseId","display_name"])
data class VisibleInstructorsDbo(
    val _class : String,
    val courseId:Int,
    val title : String,
    val name : String,
    val display_name : String,
    val job_title : String,
    val image_50x50 : String,
    val image_100x100 : String,
    val initials : String,
    val url : String
)