package com.mking11.community_project.common.api

import android.media.session.MediaSession
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mking11.community_project.common.api.domain.utils.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val BASE_URL = "https://www.udemy.com/api-2.0/"


    @Singleton
    @Provides
    fun providesGsonBuilder(): Gson {
        return GsonBuilder().create()
    }



    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS).addInterceptor(TokenInterceptor()).build()
    }


    @Singleton
    @Provides
    fun providesRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(
            BASE_URL
        ).addConverterFactory(GsonConverterFactory.create(gson)).client(client).build()
    }



}