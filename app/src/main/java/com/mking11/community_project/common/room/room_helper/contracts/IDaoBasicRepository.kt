package com.madtechet.musica.common.room.room_helper.contracts

import com.mking11.community_project.common.room.room_helper.contracts.IDaoEssentialsRepository
import kotlinx.coroutines.flow.Flow


interface IDaoBasicRepository<Dbo, PrimaryType> : IDaoEssentialsRepository<Dbo> {
    suspend fun clear()
    fun getItems(): Flow<List<@JvmSuppressWildcards Dbo>>?
    suspend fun getItem(id: PrimaryType): Dbo?
}