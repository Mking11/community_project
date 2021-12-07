package com.mking11.community_project.module.course_details.domain.model

data class CourseDetailsDto (
	val _class : String?=null,
	val id : Int?=null,
	val title : String?=null,
	val url : String?=null,
	val is_paid : Boolean?=null,
	val price : String?=null,
//	val price_detail : String?=null,
	val price_serve_tracking_id : String?=null,
	val visible_instructors : List<VisibleInstructorsDto>?=null,
	val image_125_H : String?=null,
	val image_240x135 : String?=null,
	val is_practice_test_course : Boolean?=null,
	val image_480x270 : String?=null,
	val published_title : String?=null,
	val tracking_id : String?=null,
	val predictive_score: String?=null,
	val relevancy_score: String?=null,
	val input_features: String?=null,
	val lecture_search_result: String?=null,
	val curriculum_lectures: List<String>?=null,
	val order_in_results: String?=null,
	val curriculum_items: List<String>?=null,
	val headline: String?=null,
	val instructor_name: String?=null
) {
	fun toDbo(): CourseDetailsDbo {
		return CourseDetailsDbo(
			_class, id, title, url, is_paid, price,  price_serve_tracking_id, image_125_H, image_240x135, is_practice_test_course, image_480x270, published_title, tracking_id, predictive_score, relevancy_score, input_features, lecture_search_result
		)
	}
}