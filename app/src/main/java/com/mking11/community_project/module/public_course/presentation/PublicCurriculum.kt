package com.mking11.community_project.module.public_course.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mking11.community_project.databinding.CoursepubliccirriculumBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PublicCurriculum : Fragment() {

    private lateinit var binding: CoursepubliccirriculumBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PublicCurriculumAdapter
    private val viewModel: PublicCurriculumViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CoursepubliccirriculumBinding.inflate(inflater, container, false)

        recyclerView = binding.cirriculumRecycler

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = PublicCurriculumAdapter(requireContext())
        recyclerView.adapter = adapter
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        lifecycleScope.launchWhenStarted {
            viewModel.courseId?.let { courseId ->
                viewModel.getPublicCourseResult(courseId).collect {
                    adapter.submitData(it)
                }
            }
        }
    }
}