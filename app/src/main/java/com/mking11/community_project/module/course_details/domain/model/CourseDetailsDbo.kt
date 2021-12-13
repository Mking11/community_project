package com.mking11.community_project.module.course_details.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mking11.community_project.common.utils.getCurrentDate
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Course")
@Parcelize
data class CourseDetailsDbo(
    override val _class: String? = null,
    @PrimaryKey
    override val id: Int? = null,
    override val title: String? = null,
    override val url: String? = null,
    override val is_paid: Boolean? = null,
    override val price: String? = null,
//    val price_detail: String? = null,
    override val price_serve_tracking_id: String? = null,
    override val image_125_H: String? = null,
    override val image_240x135: String? = null,
    override val is_practice_test_course: Boolean? = null,
    override val image_480x270: String? = null,
    override val published_title: String? = null,
    override val tracking_id: String? = null,
    override val predictive_score: String? = null,
    override val relevancy_score: String? = null,
    override val input_features: String? = null,
    override val lecture_search_result: String? = null,
    val curriculum_lectures: String? = null,
    override val order_in_results: String? = null,
    val curriculum_items: String? = null,
    override val headline: String? = null,
    override val instructor_name: String? = null,
    val timeStamp: String = getCurrentDate()
) : ICourseDetails,Parcelable