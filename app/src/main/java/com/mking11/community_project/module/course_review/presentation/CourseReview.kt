package com.mking11.community_project.module.course_review.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mking11.community_project.databinding.CourseReviewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CourseReview : Fragment() {


    private val courseViewModel: CourseViewModel by viewModels()
    private lateinit var adapter: CourseViewAdapter

    private lateinit var binding: CourseReviewBinding
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CourseReviewBinding.inflate(inflater, container, false)



        recyclerView = binding.reviewList
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        adapter = CourseViewAdapter(requireContext())
        recyclerView.adapter = adapter

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        lifecycleScope.launchWhenStarted {
            courseViewModel.courseId?.let { courseId ->
                courseViewModel.getCourseList(courseId).collect {
                    adapter.submitData(it)
                }
            }
        }


    }
}