package com.mking11.community_project.common.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
//    @Singleton
//    @Provides
//    fun providesCommunityDb(@ApplicationContext context: Context): CommunityDatabase {
//        return Room.databaseBuilder(context, CommunityDatabase::class.java, CommunityDatabase.DataBaseName)
//            .fallbackToDestructiveMigration().build()
//    }
//

}