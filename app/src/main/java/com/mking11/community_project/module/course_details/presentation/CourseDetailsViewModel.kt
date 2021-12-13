package com.mking11.community_project.module.course_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class CourseDetailsViewModel @Inject constructor(
    val courseDetailsUseCases: CourseDetailsUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val course = savedStateHandle.get<CourseDetailsDbo>("course")




}