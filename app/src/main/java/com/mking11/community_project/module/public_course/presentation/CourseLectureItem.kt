package com.mking11.community_project.module.public_course.presentation

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import coil.load
import com.mking11.community_project.R
import com.mking11.community_project.databinding.LectureLayoutBinding
import com.mking11.community_project.module.public_course.domain.model.PublicCourseResult
import com.mking11.community_project.module.public_course.domain.utils.CurriculumViewHolder

class CourseLectureItem(
    private val context: Context,
    private val chapterLayoutBinding: LectureLayoutBinding
) : CurriculumViewHolder(chapterLayoutBinding.root) {

    private val title = chapterLayoutBinding.title
    private val image = chapterLayoutBinding.assetImg
    private val asset = chapterLayoutBinding.assetTitle

    fun bindView(publicCourseResult: PublicCourseResult) {


        title.text = publicCourseResult.title
        val drawable: Drawable? = if (publicCourseResult.asset?.asset_type == "Video") {
            ResourcesCompat.getDrawable(
                context.resources,
                R.drawable.movie,
                null
            )
        } else {
            ResourcesCompat.getDrawable(context.resources, R.drawable.description, null)
        }
        image.load(drawable)

        asset.text = publicCourseResult.asset?.title

    }
}