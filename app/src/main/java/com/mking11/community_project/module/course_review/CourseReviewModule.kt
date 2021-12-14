package com.mking11.community_project.module.course_review

import com.mking11.community_project.module.course_review.data.data_source.CourseReviewService
import com.mking11.community_project.module.course_review.data.repository.CourseReviewRepository
import com.mking11.community_project.module.course_review.data.repository.CourseReviewRepositoryImpl
import com.mking11.community_project.module.course_review.domain.model.CourseReviewUseCases
import com.mking11.community_project.module.course_review.domain.use_case.GetCoursePagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object CourseReviewModule {

    @Provides
    @ViewModelScoped
    fun getCourseReviewService(retrofit: Retrofit): CourseReviewService {
        return retrofit.create(CourseReviewService::class.java)
    }

    @Provides
    @ViewModelScoped
    fun getCourseReviewRepository(
        courseReviewService: CourseReviewService
    ): CourseReviewRepository {
        return CourseReviewRepositoryImpl(courseReviewService)
    }


    @Provides
    @ViewModelScoped
    fun getCourseReviewUseCases(
        courseReviewRepository: CourseReviewRepository
    ): CourseReviewUseCases {
        return CourseReviewUseCases(
            coursePagingSource = GetCoursePagingSource(courseReviewRepository)
        )
    }


}