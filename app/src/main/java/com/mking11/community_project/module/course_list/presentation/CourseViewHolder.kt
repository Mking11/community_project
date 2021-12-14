package com.mking11.community_project.module.course_list.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mking11.community_project.databinding.CourseListItemBinding
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
import com.mking11.community_project.module.course_list.domain.model.CourseListInteraction

class CourseViewHolder(
    private val binding: CourseListItemBinding,
    private val courseListInteraction: CourseListInteraction,

) : RecyclerView.ViewHolder(binding.root) {




    private val photo = binding.imageView
    private val title = binding.textView2
    private val price = binding.price
    fun bind(courseDetailsDbo: CourseDetailsDbo?) {
        if (courseDetailsDbo != null) {
            photo.load(courseDetailsDbo.image_480x270)
            title.text = courseDetailsDbo.title
            price.text = courseDetailsDbo.price
        }


        binding.root.setOnClickListener {
            if (courseDetailsDbo != null) {
                courseListInteraction.onCourseItemClicked(courseDetailsDbo)
            }
        }

    }
}