package com.mking11.community_project.module.course_details.domain.model

interface ICourseDetails{
    val _class : String?
    val id : Int?
    val title : String?
    val url : String?
    val is_paid : Boolean?
    val price : String?
    //	val price_detail : String?
    val price_serve_tracking_id : String?
    val image_125_H : String?
    val image_240x135 : String?
    val is_practice_test_course : Boolean?
    val image_480x270 : String?
    val published_title : String?
    val tracking_id : String?
    val predictive_score: String?
    val relevancy_score: String?
    val input_features: String?
    val lecture_search_result: String?
    val order_in_results: String?
    val headline: String?
    val instructor_name: String?
}