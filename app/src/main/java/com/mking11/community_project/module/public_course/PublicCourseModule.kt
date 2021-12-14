package com.mking11.community_project.module.public_course

import com.mking11.community_project.module.public_course.data.data_source.PublicCourseService
import com.mking11.community_project.module.public_course.data.repository.CoursePublicCurriculumRepository
import com.mking11.community_project.module.public_course.data.repository.CoursePublicCurriculumRepositoryImpl
import com.mking11.community_project.module.public_course.domain.model.PublicCurriculumUseCases
import com.mking11.community_project.module.public_course.domain.use_case.GetCoursePagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object PublicCourseModule {


    @Provides
    @ViewModelScoped
    fun providePublicCourseService(retrofit: Retrofit): PublicCourseService {
        return retrofit.create(PublicCourseService::class.java)
    }


    @Provides
    @ViewModelScoped
    fun providePublicCourseRepository(
        publicCourseService: PublicCourseService
    ): CoursePublicCurriculumRepository {
        return CoursePublicCurriculumRepositoryImpl(publicCourseService)
    }


    @Provides
    @ViewModelScoped
    fun providePublicCourseUseCases(
        coursePublicCurriculumRepository: CoursePublicCurriculumRepository
    ): PublicCurriculumUseCases {
        return PublicCurriculumUseCases(
            getCoursePagingSource = GetCoursePagingSource(
                coursePublicCurriculumRepository
            )
        )
    }
}