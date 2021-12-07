package com.mking11.community_project.module.course_list.presentation

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mking11.community_project.databinding.CourseListItemBinding
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto

class CourseViewHolder(binding: CourseListItemBinding) : RecyclerView.ViewHolder(binding.root) {


    private val photo = binding.imageView
    private val title = binding.textView2
    private val price = binding.price
    fun bind(courseDetailsDto: CourseDetailsDto?) {
        if (courseDetailsDto != null) {
            photo.load(courseDetailsDto.image_480x270)
            title.text = courseDetailsDto.title
            price.text = courseDetailsDto.price
        }
    }
}