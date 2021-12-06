package com.mking11.community_project.common.api.domain.utils

import com.google.gson.Gson
import com.mking11.community_project.common.utils.ErrorResult
import okhttp3.ResponseBody


fun <T> convertErrorResponse(
    error: ResponseBody? = null,
    gson: Gson = Gson(),
    convertedTo: Class<T>
): T? {
    return error?.let {
        gson.fromJson(
            it.charStream(), convertedTo
        )
    }
}


fun convertErrorResponse(
    error: ResponseBody? = null,
): ErrorResult? {
    return try {
        convertErrorResponse(error = error, convertedTo = ErrorResult::class.java)
    } catch (e: Exception) {
        null
    }

}


