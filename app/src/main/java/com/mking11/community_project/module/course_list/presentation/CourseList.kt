package com.mking11.community_project.module.course_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mking11.community_project.databinding.FragmentCoruseListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CourseList : Fragment() {


    private lateinit var courseListBinding: FragmentCoruseListBinding

    val courseListViewModel: CourseListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        courseListBinding = FragmentCoruseListBinding.inflate(inflater, container, false)

        courseListViewModel.getCourseList()


        val adapter = CourseListAdapter()

        val recycler = courseListBinding.recyclerCourse
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter



        lifecycleScope.launch {
            courseListViewModel.getCourseList().collectLatest {
                adapter.submitData(it)
            }
        }

        return courseListBinding.root
    }


}