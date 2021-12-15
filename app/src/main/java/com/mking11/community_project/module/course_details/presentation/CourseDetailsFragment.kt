package com.mking11.community_project.module.course_details.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mking11.community_project.databinding.CourseDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CourseDetailsFragment : Fragment(), VisibleInstructorAdapter.CustomListeners {


    private lateinit var binding: CourseDetailsBinding

    private val viewModel: CourseDetailsViewModel by viewModels()

    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var instructorList: RecyclerView
    private lateinit var toCourse: Button
    private lateinit var toCoursePublicList: Button
    private lateinit var toCourseReview: Button

    lateinit var adapter: VisibleInstructorAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CourseDetailsBinding.inflate(inflater, container, false)


        image = binding.coursePic
        title = binding.courseTitle
        instructorList = binding.instructorList
        toCourse = binding.toCourse
        toCoursePublicList = binding.toCourseContent
        toCourseReview = binding.courseReviews




        instructorList.layoutManager = LinearLayoutManager(requireContext())

        adapter = VisibleInstructorAdapter(this)

        instructorList.adapter = adapter




        title.text = viewModel.course?.title
        image.load(viewModel.course?.image_480x270)


        toCourse.setOnClickListener {
            openChrome(viewModel.course?.url)
        }

        toCourseReview.setOnClickListener {
            handleNavigateToCourseReivew()
        }

        toCoursePublicList.setOnClickListener {
            handNavigateToCourseContent()
        }



        return binding.root
    }

    private fun handNavigateToCourseContent() {
        viewModel.course?.id?.let {
            requireView().findNavController().navigate(
                CourseDetailsFragmentDirections.actionCourseDetailsFragmentToPublicCurriculum(it)
            )
        }
    }

    private fun handleNavigateToCourseReivew() {
        viewModel.course?.id?.let {
            requireView().findNavController().navigate(
                CourseDetailsFragmentDirections.actionCourseDetailsFragmentToCourseReview(it)
            )
        }
    }


    override fun onStart() {
        super.onStart()

        lifecycleScope.launch {
            viewModel.course?.id?.let {
                println("get course id ${it}")
                viewModel.courseDetailsUseCases.getCoursesDetailsRemote(it).collect {

                    println("it not null")

                    adapter.submitList(it)

                }
            }
        }


    }

    private fun openChrome(url: String?) {
        try {
//            println("url ${url}")
//            val uri = Uri.parse(
//                BASE_URL_WEB + url
//            )
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = uri
//            startActivity(intent)


            if (url != null) {
                findNavController().navigate(
                    CourseDetailsFragmentDirections.actionCourseDetailsFragmentToWebViewFragment(
                        url
                    )
                )
            }


        } catch (e: Exception) {
            Log.e("CourseDetailsFragment", "openChrome: ${e}")
        }
    }

    override fun onItemSelected(position: Int) {

    }


}