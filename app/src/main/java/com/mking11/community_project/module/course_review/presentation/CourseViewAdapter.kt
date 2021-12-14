package com.mking11.community_project.module.course_review.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mking11.community_project.databinding.CourseReviewItemBinding

import com.mking11.community_project.module.course_review.domain.model.Result

class CourseViewAdapter(private val context: Context) :
    PagingDataAdapter<Result, CourseReviewItemViewHolder>(
        ReviewComparator
    ) {
    companion object {
        private val ReviewComparator = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.modified == newItem.modified && oldItem.rating == newItem.rating && oldItem.equals(
                    newItem
                )
            }
        }
    }

    override fun onBindViewHolder(holder: CourseReviewItemViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseReviewItemViewHolder {
        val binding = CourseReviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CourseReviewItemViewHolder(context, binding)
    }


}