package com.mking11.community_project.module.course_review.presentation

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mking11.community_project.R
import com.mking11.community_project.common.utils.getDate
import com.mking11.community_project.databinding.CourseReviewItemBinding

import com.mking11.community_project.module.course_review.domain.model.Result

class CourseReviewItemViewHolder(
    private val context: Context,
    binding: CourseReviewItemBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    private val start1 = binding.star1
    private val start2 = binding.star2
    private val start3 = binding.star3
    private val start4 = binding.star4
    private val start5 = binding.star5


    val title = binding.displayName
    private val reviewText = binding.reviewText
    private val date = binding.date


    fun bind(review: Result) {
        reviewText.text = review.content
        title.text = review.user.display_name
        date.text = getDate(review.modified)
        rate(review.rating)
    }


    private fun rate(rating: Double) {
        start1.load(draw(rating, 0))
        start2.load(draw(rating, 1))
        start3.load(draw(rating, 2))
        start4.load(draw(rating, 3))
        start5.load(draw(rating, 4))
    }

    private fun draw(value: Double, placing: Int): Drawable? {

        return when {
            value < placing.toDouble() -> {
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.empty,
                    null
                )
            }
            value >= placing.toDouble().plus(1.0) -> {
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.full,
                    null
                )
            }
            else -> {

                when (value) {
                    placing.toDouble().plus(0.5) -> {
                        ResourcesCompat.getDrawable(
                            context.resources,
                            R.drawable.half,
                            null
                        )
                    }

                    placing.toDouble() -> {
                        ResourcesCompat.getDrawable(
                            context.resources,
                            R.drawable.empty,
                            null
                        )
                    }
                    else -> {
                        ResourcesCompat.getDrawable(
                            context.resources,
                            R.drawable.empty,
                            null
                        )
                    }
                }


            }
        }


    }
}



