package com.mking11.community_project.module.course_details.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Course")
data class CourseDetailsDbo(
    val _class: String? = null,
    @PrimaryKey
    val id: Int? = null,
    val title: String? = null,
    val url: String? = null,
    val is_paid: Boolean? = null,
    val price: String? = null,
//    val price_detail: String? = null,
    val price_serve_tracking_id: String? = null,
    val image_125_H: String? = null,
    val image_240x135: String? = null,
    val is_practice_test_course: Boolean? = null,
    val image_480x270: String? = null,
    val published_title: String? = null,
    val tracking_id: String? = null,
    val predictive_score: String? = null,
    val relevancy_score: String? = null,
    val input_features: String? = null,
    val lecture_search_result: String? = null,
    val curriculum_lectures: String? = null,
    val order_in_results: String? = null,
    val curriculum_items: String? = null,
    val headline: String? = null,
    val instructor_name: String? = null
)