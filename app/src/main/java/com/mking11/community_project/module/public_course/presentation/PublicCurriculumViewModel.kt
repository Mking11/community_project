package com.mking11.community_project.module.public_course.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mking11.community_project.module.public_course.domain.model.PublicCourseList
import com.mking11.community_project.module.public_course.domain.model.PublicCourseResult
import com.mking11.community_project.module.public_course.domain.model.PublicCurriculumUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PublicCurriculumViewModel @Inject constructor(
    private val publicCurriculumUseCases: PublicCurriculumUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    val courseId: Int? = savedStateHandle.get<Int>("courseId")




    fun getPublicCourseResult(courseId: Int, pagingSize: Int = 20): Flow<PagingData<PublicCourseResult>> {
        return Pager(
            config = PagingConfig(pageSize = pagingSize, enablePlaceholders = false),
            pagingSourceFactory = {
                publicCurriculumUseCases.getCoursePagingSource(
                    courseId, pagingSize

                )
            }
        ).flow.cachedIn(viewModelScope)

    }

}