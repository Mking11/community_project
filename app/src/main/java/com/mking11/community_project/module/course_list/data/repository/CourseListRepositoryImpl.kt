package com.mking11.community_project.module.course_list.data.repository

import com.madtechet.musica.common.room.room_helper.repositories.DaoBasicRepositoryImpl
import com.mking11.community_project.common.api.domain.utils.apiCall
import com.mking11.community_project.common.room.room_helper.ScopeShared
import com.mking11.community_project.common.utils.AppResult
import com.mking11.community_project.module.course_details.data.repository.CourseRepository
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto
import com.mking11.community_project.module.course_list.data.data_source.CourseIndexDao
import com.mking11.community_project.module.course_list.data.data_source.CourseListService
import com.mking11.community_project.module.course_list.domain.model.CourseRemoteIndexDbo
import com.mking11.community_project.module.course_list.domain.model.CourseListDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response

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
            services.getCourseList(page, pageSize, category, subCategory, search, price, language)
        }

    }

    override suspend fun getCourseListIndex(id: Int): CourseRemoteIndexDbo? {
        return courseIndexDao.getCourseList(id)
    }

    override suspend fun insertCourseListIndex(list: List<CourseRemoteIndexDbo>) {
        list.forEach {
            insertCourseList(it)
        }
    }

    override suspend fun insertCourseList(
        courseRemoteIndexDbo: CourseRemoteIndexDbo
    ) {
        super.insertOrUpdate(
            courseRemoteIndexDbo,
            scope.scope,
            scope.handler,
            courseIndexDao
        )
    }

    override suspend fun getCourseListRemoteResponse(
        page: Int,
        pageSize: Int,
        category: String?,
        subCategory: String?,
        search: String?,
        price: String?,
        language: String?
    ): Response<CourseListDto> {
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

    override suspend fun insertList(list: List<CourseDetailsDto>) {
        list.forEach {
            courseRepository.insertCourse(it)
        }
    }


    override suspend fun clearTables() {
        scope.scope.launch {
            courseIndexDao.clearCourseIndex()
            courseRepository.clearCourseTable()
        }

    }


}