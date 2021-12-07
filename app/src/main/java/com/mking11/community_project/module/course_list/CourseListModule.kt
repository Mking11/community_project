package com.mking11.community_project.module.course_list

import com.mking11.community_project.module.course_details.data.repository.CourseRepository
import com.mking11.community_project.module.course_list.data.data_source.CourseListService
import com.mking11.community_project.module.course_list.data.repository.CourseListRepository
import com.mking11.community_project.module.course_list.data.repository.CourseListRepositoryImpl
import com.mking11.community_project.module.course_list.domain.model.CourseListUseCases
import com.mking11.community_project.module.course_list.domain.use_case.GetCoursesRemote
import com.mking11.community_project.module.course_list.domain.use_case.InsertCourses
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object CourseListModule {

    @Provides
    @ViewModelScoped
    fun providesCoursesRemote(retrofit: Retrofit): CourseListService {
        return retrofit.create(CourseListService::class.java)
    }


    @Provides
    @ViewModelScoped
    fun provideCourseRepository(
        service: CourseListService,
        repository: CourseRepository
    ): CourseListRepository {
        return CourseListRepositoryImpl(service, repository)
    }


    @Provides
    @ViewModelScoped
    fun provideCourseListUseCases(
        courseRepository: CourseRepository,
        courseListRepository: CourseListRepository,
    ): CourseListUseCases {
        return CourseListUseCases(
            getCoursesRemote = GetCoursesRemote(courseListRepository),
            insertCourses = InsertCourses(courseRepository)
        )
    }

}