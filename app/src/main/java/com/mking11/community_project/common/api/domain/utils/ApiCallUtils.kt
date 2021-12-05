package com.mking11.community_project.common.api.domain.utils

import com.madtechet.musica.common.utils.AppResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

suspend fun safeApiCall(
    apiCall: suspend () -> Response<ApiResponse>,
): AppResult<ApiResponse> {

    return  try {
        val response = apiCall()
       if (response.isSuccessful) {
             val body = response.body()
             if (body != null) {
                 AppResult.Success(body)
             } else {
                 AppResult.Success(ApiResponse(message = "Success"))
             }
         } else {
             val error = convertErrorResponse(response.errorBody())
             AppResult.Failure(error, error?.message ?: "Unknown Error Occurred", error?.errors)
         }


    } catch (e: Exception) {
        AppResult.Error.NonRecoverableError(e)
    }


}

fun apiCall(
    call: suspend () -> Response<ApiResponse>,
): Flow<AppResult<ApiResponse>> =
    flow {
        emit(AppResult.InProgress)
        emit(safeApiCall(call))
    }
