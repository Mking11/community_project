package com.mking11.community_project.module.course_list.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.ExperimentalPagingApi
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

    val courseListViewModel: CourseListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        courseListBinding = FragmentCoruseListBinding.inflate(inflater, container, false)


        adapter = CourseListAdapter(this)

        val recycler = courseListBinding.recyclerCourse
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter











        courseListBinding.button.visibility = View.GONE
        courseListBinding.progressBar.visibility = View.GONE


        setHasOptionsMenu(true)

        lifecycleScope.launch{
            courseListViewModel.getCourseList(
                search = null, category = null, subcategory = null
            ).collectLatest {
                println("new data $it")
                adapter.submitData(it)
            }
        }

        return courseListBinding.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onCourseItemClicked(courseDetailsDbo: CourseDetailsDbo) {
        requireView().findNavController()
            .navigate(CourseListDirections.actionCourseListToCourseDetailsFragment(courseDetailsDbo))
    }


}