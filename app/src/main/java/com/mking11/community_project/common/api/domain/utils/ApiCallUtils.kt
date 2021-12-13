package com.mking11.community_project.common.api.domain.utils

import com.mking11.community_project.common.utils.AppResult
import com.mking11.community_project.common.utils.ErrorResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>,
): AppResult<T> {

    return try {
        val response: Response<T> = apiCall()
        if (response.isSuccessful) {
            val body: T? = response.body()
            AppResult.Success(body)
        } else {
            val error: ErrorResult? = convertErrorResponse(response.errorBody())

            println("error meessage $error")
            AppResult.Failure(null, error?.detail.toString())
        }
    } catch (e: Exception) {
        AppResult.Error.NonRecoverableError(e)
    }
}

//
fun <T> apiCall(
    call: suspend () -> Response<T>,
): Flow<AppResult<T>> =
    flow {
        emit(AppResult.InProgress)
        emit(safeApiCall(call))
    }
