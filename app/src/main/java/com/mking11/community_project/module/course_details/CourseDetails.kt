package com.mking11.community_project.module.course_details

import com.mking11.community_project.common.room.CommunityDatabase
import com.mking11.community_project.module.course_details.data.data_source.CourseDao
import com.mking11.community_project.module.course_details.data.data_source.CourseDetailService
import com.mking11.community_project.module.course_details.data.repository.CourseRepository
import com.mking11.community_project.module.course_details.data.repository.CourseRepositoryImpl
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsUseCases
import com.mking11.community_project.module.course_details.domain.use_case.GetCoursesDetailsRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object CourseDetails {

    @Provides
    @ViewModelScoped
    fun provideCourseDao(
        database: CommunityDatabase
    ): CourseDao {
        return database.courseDao
    }

    @Provides
    @ViewModelScoped
    fun provideCourseDetails(retrofit: Retrofit): CourseDetailService {
        return retrofit.create(CourseDetailService::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideCourseRepository(
        dao: CourseDao,
        service: CourseDetailService
    ): CourseRepository {
        return CourseRepositoryImpl(courseDao = dao, courseDetailsService = service)
    }


    @Provides
    @ViewModelScoped
    fun provideCourseUseCases(
        courseRepository: CourseRepository
    ): CourseDetailsUseCases =
        CourseDetailsUseCases(getCoursesDetailsRemote = GetCoursesDetailsRemote(courseRepository))


}