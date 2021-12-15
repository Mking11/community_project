package com.mking11.community_project.module.course_list

import com.mking11.community_project.common.room.CommunityDatabase
import com.mking11.community_project.module.course_details.data.repository.CourseRepository
import com.mking11.community_project.module.course_details.data.repository.VisibleInstructionsRepository
import com.mking11.community_project.module.course_list.data.data_source.CourseIndexDao
import com.mking11.community_project.module.course_list.data.data_source.CourseListService
import com.mking11.community_project.module.course_list.data.repository.CourseListRepository
import com.mking11.community_project.module.course_list.data.repository.CourseListRepositoryImpl
import com.mking11.community_project.module.course_list.domain.model.CourseListUseCases
import com.mking11.community_project.module.course_list.domain.use_case.GetCourseDatabaseSearch
import com.mking11.community_project.module.course_list.domain.use_case.GetCoursesDatabase
import com.mking11.community_project.module.course_list.domain.use_case.GetCoursesRemoteMediator
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
    fun provideCourseRemoteIndex(database: CommunityDatabase): CourseIndexDao {
        return database.courseIndexDao
    }


    @Provides
    @ViewModelScoped
    fun provideCourseRepository(
        service: CourseListService,
        courseIndexDao: CourseIndexDao,
        repository: CourseRepository
    ): CourseListRepository {
        return CourseListRepositoryImpl(service, courseIndexDao, repository)
    }


    @Provides
    @ViewModelScoped
    fun provideCourseListUseCases(
        courseRepository: CourseRepository,
        courseListRepository: CourseListRepository,
        visibleInstructionsRepository: VisibleInstructionsRepository,
        database: CommunityDatabase
    ): CourseListUseCases {
        return CourseListUseCases(
            getCoursesRemoteMediator = GetCoursesRemoteMediator(
                courseListRepository,
                visibleInstructionsRepository,
                database
            ),
            insertCourses = InsertCourses(courseRepository),
            getCoursesDatabase = GetCoursesDatabase(courseListRepository),
            getCourseDatabaseSearch = GetCourseDatabaseSearch(courseListRepository)
        )
    }

}