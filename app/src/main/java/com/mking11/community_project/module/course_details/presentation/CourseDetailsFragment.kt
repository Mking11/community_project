package com.mking11.community_project.module.course_details.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Browser
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



        return binding.root
    }


    override fun onStart() {
        super.onStart()

        lifecycleScope.launch {
            viewModel.course?.id?.let {
                println("get course id ${it}")
                viewModel.courseDetailsUseCases.getCoursesDetailsRemote(it).collect {

                    if (it != null) {
                        println("it not null")

                        it.visible_instructors?.let { it1 -> adapter.submitList(it1) }
                    }

                }
            }
        }


    }

    private fun openChrome(url: String?) {
        try {
            val context: Context = requireActivity()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intent.putExtra(Browser.EXTRA_APPLICATION_ID, context.packageName)

            startActivity(intent)
        } catch (e: Exception) {
            Log.e("CourseDetailsFragment", "openChrome: ${e}")
        }
    }

    override fun onItemSelected(position: Int) {

    }


}