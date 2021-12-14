package com.mking11.community_project.module.course_details.domain.model

data class CourseDetailsDto(
    override val _class: String? = null,
    override val id: Int? = null,
    override val title: String? = null,
    override val url: String? = null,
    override val is_paid: Boolean? = null,
    override val price: String? = null,
//	val price_detail : String?=null,
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
    override val order_in_results: String? = null,
    override val headline: String? = null,
    override val instructor_name: String? = null,
    val visible_instructors: List<VisibleInstructorsDto>? = null,
    val curriculum_items: List<String>? = null,
    val curriculum_lectures: List<String>? = null
) : ICourseDetails {
    fun toDbo(subcategory: String?, search: String?): CourseDetailsDbo {
        return CourseDetailsDbo(
            _class,
            id,
            title,
            url,
            is_paid,
            price,
            price_serve_tracking_id,
            image_125_H,
            image_240x135,
            is_practice_test_course,
            image_480x270,
            published_title,
            tracking_id,
            predictive_score,
            relevancy_score,
            input_features,
            lecture_search_result,
            subcategory = subcategory,
            search = search
        )
    }
}