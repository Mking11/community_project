package com.mking11.community_project.module.course_list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mking11.community_project.databinding.CourseListItemBinding
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto

class CourseListAdapter : PagingDataAdapter<CourseDetailsDto, CourseViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<CourseDetailsDto>() {
            override fun areItemsTheSame(
                oldItem: CourseDetailsDto,
                newItem: CourseDetailsDto
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CourseDetailsDto,
                newItem: CourseDetailsDto
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding =
            CourseListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }
}