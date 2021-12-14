package com.mking11.community_project.module.public_course.presentation

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import coil.load
import com.mking11.community_project.R
import com.mking11.community_project.databinding.CourseQuizItemBinding
import com.mking11.community_project.module.public_course.domain.model.PublicCourseResult
import com.mking11.community_project.module.public_course.domain.utils.CurriculumViewHolder

class CourseQuiz(
    private val context: Context,
    private val binding: CourseQuizItemBinding
) : CurriculumViewHolder(binding.root) {

    val image = binding.assetImg
    val title = binding.assetTitle
    fun onBind(publicCourseResult: PublicCourseResult) {
        title.text = publicCourseResult.title

        image.load(
            ResourcesCompat.getDrawable(
                context.resources,
                R.drawable.quiz,
                null
            )
        )
    }
}