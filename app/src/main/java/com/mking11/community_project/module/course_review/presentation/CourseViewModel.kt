package com.mking11.community_project.module.course_review.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mking11.community_project.module.course_review.domain.model.CourseReviewUseCases
import com.mking11.community_project.module.course_review.domain.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val courseReviewUseCases: CourseReviewUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val courseId = savedStateHandle.get<Int>("courseId")


    fun getCourseList(courseId: Int, pagingSize: Int = 20): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(pageSize = pagingSize, enablePlaceholders = false),
            pagingSourceFactory = {
                courseReviewUseCases.coursePagingSource(
                    courseId, pagingSize

                )
            }
        ).flow.cachedIn(viewModelScope)

    }


}