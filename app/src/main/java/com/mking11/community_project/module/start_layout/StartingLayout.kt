package com.mking11.community_project.module.start_layout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.mking11.community_project.databinding.StartingLayoutBinding
import com.mking11.community_project.module.start_layout.data.data_source.SubcategoryList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartingLayout : Fragment(), StartingLayoutAdapter.CustomListeners {
    private lateinit var binding: StartingLayoutBinding
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: StartingLayoutAdapter

    private lateinit var searchView: TextInputLayout
    private lateinit var searchInput: TextInputEditText
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StartingLayoutBinding.inflate(inflater, container, false)

        searchInput = binding.searchInput
        searchView = binding.searchView


        searchView.setEndIconOnClickListener {
            search()
        }
        searchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ) {
                search()
            }
            true
        }

        recycler = binding.subcatgoryLayou
        recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = StartingLayoutAdapter(this)
        recycler.adapter = adapter

        adapter.submitList(SubcategoryList)


        return binding.root
    }

    private fun search() {
        searchView.error = null
        if (searchInput.text!!.isEmpty()) {
            searchInput.error = "Required"
            searchView.requestFocus()
        } else {
            handleNavigateOut(null, searchInput.text.toString())
        }
    }

    fun handleNavigateOut(category: String?, search: String?) {
        findNavController().navigate(
            StartingLayoutDirections.actionStartingLayoutToCourseList(
                category,
                search
            )
        )
    }

    override fun onItemSelected(position: String) {
        handleNavigateOut(position, null)
    }
}