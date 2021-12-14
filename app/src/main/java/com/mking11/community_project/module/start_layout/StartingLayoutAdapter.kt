package com.mking11.community_project.module.start_layout

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.mking11.community_project.databinding.SubcatagoryItemBinding

class StartingLayoutAdapter(private val customListeners: CustomListeners) :
    RecyclerView.Adapter<StartingLayoutAdapter.StartingLayoutViewHolder>() {

    private val diffUtilItemCallback = object : DiffUtil.ItemCallback<String>() {

        //pk is the primary key for the data class.
        //replace with any unique identifier of your specific data class.
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    private val listDiffer = AsyncListDiffer(this, diffUtilItemCallback)

    private lateinit var binding: SubcatagoryItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartingLayoutViewHolder {
        binding =
            SubcatagoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StartingLayoutViewHolder(binding, customListeners)
    }

    override fun onBindViewHolder(holder: StartingLayoutViewHolder, position: Int) {
        holder.bind(listDiffer.currentList[position])
    }

    override fun getItemCount(): Int {
        return listDiffer.currentList.size
    }

    fun submitList(list: List<String>) {
        listDiffer.submitList(list)
    }

    class StartingLayoutViewHolder
    constructor(
        private val binding: SubcatagoryItemBinding,
        private val customListeners: CustomListeners
    ) : RecyclerView.ViewHolder(binding.root) {

        val text = binding.subcatgoryItem

        fun bind(item: String) {

            text.text = item
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
        fun onItemSelected(position: String)
        // add your view listeners here
        // ex : fun onSwitchChecked(..) , fun onItemLongPress(..)
    }
}
