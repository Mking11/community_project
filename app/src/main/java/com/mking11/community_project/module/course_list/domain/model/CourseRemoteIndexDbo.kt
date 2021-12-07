package com.mking11.community_project.module.course_list.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "CourseIndex")
class CourseRemoteIndexDbo(
    @PrimaryKey
    val id:Int,
    val prevKey: Int?,
    val nextKey: Int?
)