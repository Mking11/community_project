package com.mking11.community_project.module.course_list.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mking11.community_project.databinding.CourseListItemBinding
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
import com.mking11.community_project.module.course_list.domain.model.CourseListInteraction


class CourseListAdapter(private val interaction: CourseListInteraction) :
    PagingDataAdapter<CourseDetailsDbo, CourseViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<CourseDetailsDbo>() {
            override fun areItemsTheSame(
                oldItem: CourseDetailsDbo,
                newItem: CourseDetailsDbo
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CourseDetailsDbo,
                newItem: CourseDetailsDbo
            ): Boolean =
                oldItem.title == newItem.title && oldItem.equals(newItem)
        }
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding =
            CourseListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding, interaction)
    }
}