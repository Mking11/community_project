package com.mking11.community_project.module.public_course.domain.model

data class  PublicCourseList (
	val count : Int,
	val next : String,
	val previous : String,
	val results : List<PublicCourseResult>
)


