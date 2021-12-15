package com.mking11.community_project.module.course_details.data.repository

import androidx.paging.PagingSource
import com.mking11.community_project.common.room.room_helper.repositories.DaoEssentialsRepositoryImpl
import com.mking11.community_project.common.api.domain.utils.apiCall
import com.mking11.community_project.common.room.room_helper.ScopeShared
import com.mking11.community_project.common.utils.AppResult
import com.mking11.community_project.module.course_details.data.data_source.CourseDao
import com.mking11.community_project.module.course_details.data.data_source.CourseDetailService
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CourseRepositoryImpl(
    private val courseDao: CourseDao,

) : CourseRepository,
    DaoEssentialsRepositoryImpl<CourseDetailsDbo> {

    private val scope = ScopeShared(this.javaClass, CoroutineScope(Dispatchers.IO))
    override fun insertCourse(courseDetailsDto: CourseDetailsDto,subcategory:String?,search: String?) {
        super.insertOrUpdate(courseDetailsDto.toDbo(subcategory, search), scope.scope, scope.handler, courseDao)
    }


    override fun getCoursePaging(search: String?,subcategory: String?): PagingSource<Int, CourseDetailsDbo> {
        return courseDao.getCourses(search, subcategory)
    }

    override fun getCoursePaging(search: String): PagingSource<Int, CourseDetailsDbo> {
        return courseDao.getCourses(search)
    }


    override suspend fun clearCourseTable() {
        scope.scope.launch {
            courseDao.clear()
        }
    }
}