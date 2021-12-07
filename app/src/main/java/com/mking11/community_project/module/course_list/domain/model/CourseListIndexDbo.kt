package com.mking11.community_project.module.course_list.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "CourseIndex")
class CourseIndexDbo(
    @PrimaryKey
    val lastRequestId: String,
    val prevKey: Int?,
    val nextKey: Int?
)