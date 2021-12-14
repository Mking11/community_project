package com.mking11.community_project.module.course_list.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
import com.mking11.community_project.module.course_list.domain.model.CourseListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CourseListViewModel @Inject constructor(
    private val courseListUseCases: CourseListUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val search = savedStateHandle.get<String>("search")
    val subcategory = savedStateHandle.get<String>("subcategory")


    @ExperimentalPagingApi
    fun getCourseList(
        search: String?,
        pageSize: Int = 20,
        category: String?,
        subcategory: String?,
        language: String = "en"
    ): Flow<PagingData<CourseDetailsDbo>> {
        val factory: () -> PagingSource<Int, CourseDetailsDbo> =
            { courseListUseCases.getCoursesDatabase(search, subcategory) }
        return Pager(
            config = PagingConfig(pageSize = pageSize, enablePlaceholders = true),
            remoteMediator = courseListUseCases.getCoursesRemoteMediator(
                pageSize,
                category,
                subcategory,
                search,
                language = language
            ), pagingSourceFactory = factory, initialKey = 1
        ).flow.cachedIn(viewModelScope)

    }
}