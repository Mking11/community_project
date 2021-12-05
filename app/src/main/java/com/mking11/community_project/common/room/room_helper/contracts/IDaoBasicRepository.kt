package com.madtechet.musica.common.room.room_helper.contracts

import kotlinx.coroutines.flow.Flow


interface IDaoBasicRepository<Dbo, PrimaryType> : IDaoEssentialsRepository<Dbo, PrimaryType> {
    suspend fun clear()
    fun getItems(): Flow<List<@JvmSuppressWildcards Dbo>>?
    suspend fun getItem(id: PrimaryType): Dbo?
}