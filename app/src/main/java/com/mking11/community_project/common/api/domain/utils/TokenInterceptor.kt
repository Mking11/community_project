package com.mking11.community_project.common.api.domain.utils

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Content-Type", "application/json")
        requestBuilder.addHeader(
            "Authorization",
            "Basic OVQzV0hQa0ZlUEw0SjQzc0liME1lcURXMHo3UWIzYUx2QkVqWWJJajpqbGs4MlJROTZxcDBlQldSbjVzOEY5NDVXRFNiTlJxNGlVY3NEUTY4YTF4SlZKdnJFdFhQRjlOSzZPYklvZzdEbUxMMnNlWjFsTDdVVU5JYUFDZmNIWHoweENjODJuY3RGVk9FN2I0VThreEZmbmt0dmVmdGhmajd3SVhTUGlGZw=="
        )
        return chain.proceed(requestBuilder.build())
    }
}