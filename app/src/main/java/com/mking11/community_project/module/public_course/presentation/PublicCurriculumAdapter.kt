package com.mking11.community_project.module.public_course.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mking11.community_project.databinding.ChapterLayoutBinding
import com.mking11.community_project.databinding.CourseQuizItemBinding
import com.mking11.community_project.databinding.LectureLayoutBinding
import com.mking11.community_project.module.public_course.domain.model.PublicCourseResult
import com.mking11.community_project.module.public_course.domain.utils.CurriculumViewHolder

class PublicCurriculumAdapter
    (private val context: Context) : PagingDataAdapter<PublicCourseResult, CurriculumViewHolder>(
    PublicCourseResultComparator
) {

    companion object {
        private val PublicCourseResultComparator =
            object : DiffUtil.ItemCallback<PublicCourseResult>() {
                override fun areItemsTheSame(
                    oldItem: PublicCourseResult,
                    newItem: PublicCourseResult
                ): Boolean {
                    return oldItem.id == newItem.id

                }

                override fun areContentsTheSame(
                    oldItem: PublicCourseResult,
                    newItem: PublicCourseResult
                ): Boolean {
                    return oldItem == newItem && oldItem.asset == newItem.asset
                }

            }
    }

    override fun getItemViewType(position: Int): Int {
        val item: PublicCourseResult? = getItem(position)
        return when (item?._class) {
            "chapter" -> {
                0
            }
            "quiz" -> {
                2
            }
            "lecture" -> {
                1
            }
            else -> {
                3
            }
        }

    }


    override fun onBindViewHolder(holder: CurriculumViewHolder, position: Int) {
        getItem(position)?.let {
            when (holder.itemViewType) {
                0 -> {
                    (holder as ChapterViewHolder).onBind(it)
                }
                1 -> {
                    (holder as CourseLectureItem).bindView(it)
                }
                2 -> {
                    (holder as CourseQuiz).onBind(it)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurriculumViewHolder {
        return when (viewType) {
            0 -> {
                ChapterViewHolder(
                    binding = ChapterLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            1 -> {
                CourseLectureItem(
                    context,
                    chapterLayoutBinding = LectureLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            2 -> {
                CourseQuiz(
                    context,
                    binding = CourseQuizItemBinding.inflate(
                        LayoutInflater.from(context),
                        parent,
                        false
                    )
                )
            }


            else -> {
                ChapterViewHolder(
                    binding = ChapterLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }
}
