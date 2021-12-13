package com.mking11.community_project.module.course_details.domain.use_case

import android.util.Log
import com.mking11.community_project.common.utils.AppResult
import com.mking11.community_project.module.course_details.data.repository.CourseRepository
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class GetCoursesDetailsRemote(private val courseRepository: CourseRepository) {
    private val TAG = "GetCoursesDetailsRemote"
    @ExperimentalCoroutinesApi
    suspend operator fun invoke(courseId: Int): Flow<CourseDetailsDto?> = callbackFlow {


        courseRepository.getCourseDetails(courseId).catch { e ->
            Log.e(TAG, "invoke: ", e)
        }.collect {

            println("response $${it}")
            if (it is AppResult.Error.NonRecoverableError){
                println("it ${it.exception}")
            }
            if (it is AppResult.Success) {
                this.trySend(it.data)
            }
        }

        awaitClose { }


    }
}