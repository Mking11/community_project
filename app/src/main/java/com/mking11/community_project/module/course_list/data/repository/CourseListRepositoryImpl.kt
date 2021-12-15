package com.mking11.community_project.module.course_list.data.repository

import androidx.paging.PagingSource
import com.mking11.community_project.common.room.room_helper.repositories.DaoBasicRepositoryImpl
import com.mking11.community_project.common.api.domain.utils.apiCall
import com.mking11.community_project.common.room.room_helper.ScopeShared
import com.mking11.community_project.common.utils.AppResult
import com.mking11.community_project.module.course_details.data.repository.CourseRepository
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto
import com.mking11.community_project.module.course_list.data.data_source.CourseIndexDao
import com.mking11.community_project.module.course_list.data.data_source.CourseListService
import com.mking11.community_project.module.course_list.domain.model.CourseListDto
import com.mking11.community_project.module.course_list.domain.model.CourseRemoteIndexDbo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*

class CourseListRepositoryImpl(
    private val services: CourseListService,
    private val courseIndexDao: CourseIndexDao,
    private val courseRepository: CourseRepository
) : CourseListRepository, DaoBasicRepositoryImpl<CourseRemoteIndexDbo, String> {

    private val scope = ScopeShared(this::class.java, CoroutineScope(Dispatchers.IO))
    override fun getCourseListRemote(
        page: Int,
        pageSize: Int,
        category: String?,
        subCategory: String?,
        search: String?,
        price: String?,
        language: String?
    ): Flow<AppResult<CourseListDto>> {
        return apiCall {
            services.getCourseListResponse(page, pageSize, category, subCategory, search, price, language)
        }

    }

    override suspend fun getCourseListIndex(id: Int): CourseRemoteIndexDbo? {
        return courseIndexDao.getCourseList(id)
    }

    override fun insertCourseListIndex(list: List<CourseRemoteIndexDbo>) {

        list.forEach {
            insertCourseList(it)
        }
    }

    override fun insertCourseList(
        courseRemoteIndexDbo: CourseRemoteIndexDbo
    ) {
        super.insertOrUpdate(
            courseRemoteIndexDbo,
            scope.scope,
            scope.handler,
            courseIndexDao
        )
    }

    override fun getCourseBySearch(title: String): PagingSource<Int, CourseDetailsDbo> {
        return courseRepository.getCoursePaging(title.lowercase(Locale.getDefault()))
    }

    override fun getCourses(search: String?,subcategory: String?): PagingSource<Int, CourseDetailsDbo> {
        return courseRepository.getCoursePaging(search,subcategory)
    }

    override suspend fun getCourseListRemoteResponse(
        page: Int,
        pageSize: Int,
        category: String?,
        subCategory: String?,
        search: String?,
        price: String?,
        language: String?
    ): CourseListDto {
        return services.getCourseList(
            page,
            pageSize,
            category,
            subCategory,
            search,
            price,
            language
        )
    }

    override fun insertList(list: List<CourseDetailsDto>,search: String?,subCategory: String?) {
        list.forEach {
            courseRepository.insertCourse(it,subCategory,search)
        }
    }


    override suspend fun clearData() {
        scope.scope.launch {
            courseRepository.clearCourseTable()
        }

    }

    override suspend fun clearKeys() {
        scope.scope.launch {
            courseIndexDao.clearCourseIndex()
        }
    }


}