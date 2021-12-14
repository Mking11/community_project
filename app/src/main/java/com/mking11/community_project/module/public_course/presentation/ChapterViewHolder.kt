package com.mking11.community_project.module.public_course.presentation

import com.mking11.community_project.databinding.ChapterLayoutBinding
import com.mking11.community_project.module.public_course.domain.model.PublicCourseResult
import com.mking11.community_project.module.public_course.domain.utils.CurriculumViewHolder

class ChapterViewHolder(private val binding: ChapterLayoutBinding) :
    CurriculumViewHolder(binding.root) {

    private val chapter = binding.chapterTitle

    fun onBind(publicCourseResult: PublicCourseResult) {
        chapter.text = publicCourseResult.title
    }
}