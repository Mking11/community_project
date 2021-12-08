package com.mking11.community_project.module.course_list.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.flatMap
import androidx.recyclerview.widget.LinearLayoutManager
import com.mking11.community_project.R
import com.mking11.community_project.databinding.FragmentCoruseListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
@AndroidEntryPoint
class CourseList : Fragment() {


    private lateinit var courseListBinding: FragmentCoruseListBinding
    private lateinit var adapter: CourseListAdapter

    val courseListViewModel: CourseListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        courseListBinding = FragmentCoruseListBinding.inflate(inflater, container, false)


        adapter = CourseListAdapter()

        val recycler = courseListBinding.recyclerCourse
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter



        courseListBinding.button.setOnClickListener {
            adapter.retry()
        }


        courseListBinding.button.visibility = View.GONE
        courseListBinding.progressBar.visibility = View.GONE

        adapter.addLoadStateListener { loadState ->

            println("load state $loadState")

        }
        setHasOptionsMenu(true)

        return courseListBinding.root
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            courseListViewModel.getCourseList(
                search = null, category = null, subcategory = null
            ).collectLatest {
                println("new data $it")
                adapter.submitData(it)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


}