package com.mking11.community_project.module.course_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto
import com.mking11.community_project.module.course_list.domain.model.CourseListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CourseListViewModel @Inject constructor(
    private val courseListUseCases: CourseListUseCases
) : ViewModel() {


    fun getCourseList(): Flow<PagingData<CourseDetailsDto>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = {
                courseListUseCases.getCoursesRemote(
                    1,
                    20,
                    language = "en",
                    price = "price-free",
                    category = null,
                    subCategory = null
                )
            }
        ).flow.cachedIn(viewModelScope)

    }
}