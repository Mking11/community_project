package com.mking11.community_project.module.course_details.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mking11.community_project.databinding.InstructorLayoutBinding
import com.mking11.community_project.module.course_details.domain.model.VisibleInstructorsDto

class VisibleInstructorAdapter(private val customListeners: CustomListeners) :
    RecyclerView.Adapter<VisibleInstructorAdapter.VisibleInstructorViewHolder>() {

    private val diffUtilItemCallback = object : DiffUtil.ItemCallback<VisibleInstructorsDto>() {

        //pk is the primary key for the data class.
        //replace with any unique identifier of your specific data class.
        override fun areItemsTheSame(
            oldItem: VisibleInstructorsDto,
            newItem: VisibleInstructorsDto
        ): Boolean {
            return oldItem.display_name == newItem.display_name
        }

        override fun areContentsTheSame(
            oldItem: VisibleInstructorsDto,
            newItem: VisibleInstructorsDto
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val listDiffer = AsyncListDiffer(this, diffUtilItemCallback)

    private lateinit var binding: InstructorLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisibleInstructorViewHolder {
        binding =
            InstructorLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VisibleInstructorViewHolder(binding, customListeners)
    }

    override fun onBindViewHolder(holder: VisibleInstructorViewHolder, position: Int) {
        holder.bind(listDiffer.currentList[position])
    }

    override fun getItemCount(): Int {
        return listDiffer.currentList.size
    }

    fun submitList(list: List<VisibleInstructorsDto>) {
        listDiffer.submitList(list)
    }

    class VisibleInstructorViewHolder
    constructor(
        private val binding: InstructorLayoutBinding,
        private val customListeners: CustomListeners
    ) : RecyclerView.ViewHolder(binding.root) {


        val image = binding.instructorPhoto
        val title = binding.instructorTitle
        val name = binding.instructorName
        fun bind(item: VisibleInstructorsDto) {

            //Custom onClick for whole item onClick


            image.load(item.image_100x100)
            title.text = item.title
            name.text = item.display_name
            //Use CustomListeners respective function for respective view's listeners.
        }
    }

    // Interface to be inherited by view to provide 
    //custom listeners for each item based on position 
    //or other custom parameters (ex : Primary key)

    interface CustomListeners {
        fun onItemSelected(position: Int)
        // add your view listeners here
        // ex : fun onSwitchChecked(..) , fun onItemLongPress(..)
    }
}
