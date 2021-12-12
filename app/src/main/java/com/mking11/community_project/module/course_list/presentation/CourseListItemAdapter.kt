package com.mking11.community_project.module.course_list.presentation

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.mking11.community_project.databinding.CourseListItemBinding
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo

class CourseListItemAdapter(private val customListeners: CustomListeners) :
    RecyclerView.Adapter<CourseListItemAdapter.CourseVewHolder>() {

    private val diffUtilItemCallback = object : DiffUtil.ItemCallback<CourseDetailsDbo>() {

        //pk is the primary key for the data class.
        //replace with any unique identifier of your specific data class.
        override fun areItemsTheSame(
            oldItem: CourseDetailsDbo,
            newItem: CourseDetailsDbo
        ): Boolean {
            return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CourseDetailsDbo,
            newItem: CourseDetailsDbo
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val listDiffer = AsyncListDiffer(this, diffUtilItemCallback)

    private lateinit var binding: CourseListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseVewHolder {
        binding = CourseListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseVewHolder(binding, customListeners)
    }

    override fun onBindViewHolder(holder: CourseVewHolder, position: Int) {
        holder.bind(listDiffer.currentList[position])
    }

    override fun getItemCount(): Int {
        return listDiffer.currentList.size
    }

    fun submitList(list: List<CourseDetailsDbo>) {
        listDiffer.submitList(list)
    }

    class CourseVewHolder
    constructor(
        private val binding: CourseListItemBinding,
        private val customListeners: CustomListeners
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CourseDetailsDbo) {

            //Custom onClick for whole item onClick
            binding.root.setOnClickListener {
                //Pass respective parameter, adapterPosition or pk.
                customListeners.onItemSelected(item)
            }

            //TODO : Bind your data to views here.
            //Use CustomListeners respective function for respective view's listeners.
        }
    }

    // Interface to be inherited by view to provide
    //custom listeners for each item based on position
    //or other custom parameters (ex : Primary key)

    interface CustomListeners {
        fun onItemSelected(course:CourseDetailsDbo)
        // add your view listeners here
        // ex : fun onSwitchChecked(..) , fun onItemLongPress(..)
    }
}
