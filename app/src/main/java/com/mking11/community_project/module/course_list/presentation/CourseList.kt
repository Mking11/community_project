package com.mking11.community_project.module.course_list.presentation

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.mking11.community_project.R
import com.mking11.community_project.databinding.FragmentCoruseListBinding
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
import com.mking11.community_project.module.course_list.domain.model.CourseListInteraction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
@AndroidEntryPoint
class CourseList : Fragment(), CourseListInteraction {


    private lateinit var courseListBinding: FragmentCoruseListBinding
    private lateinit var adapter: CourseListAdapter

    private lateinit var progressBar: ProgressBar
    val courseListViewModel: CourseListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        courseListBinding = FragmentCoruseListBinding.inflate(inflater, container, false)

        progressBar = courseListBinding.progressBar
        adapter = CourseListAdapter(this)

        val recycler = courseListBinding.recyclerCourse
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter





        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {

                progressBar.isVisible = it.refresh is LoadState.Loading
            }


        }

        adapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter(adapter::retry),
            footer = ReposLoadStateAdapter(adapter::retry)
        )








        courseListBinding.progressBar.visibility = View.GONE


        setHasOptionsMenu(true)

        lifecycleScope.launch {
            courseListViewModel.getCourseList(
                search = courseListViewModel.search,
                subcategory = courseListViewModel.subcategory,
                category = null
            ).collectLatest {
                adapter.submitData(it)
            }
        }

        return courseListBinding.root
    }



    override fun onCourseItemClicked(courseDetailsDbo: CourseDetailsDbo) {
        requireView().findNavController()
            .navigate(CourseListDirections.actionCourseListToCourseDetailsFragment(courseDetailsDbo))
    }


}