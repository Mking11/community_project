package com.madtechet.musica.common.room.room_helper.contracts

import kotlinx.coroutines.flow.Flow

interface IDaoCompleteRepository<Dbo, PrimaryType>: IDaoBasicRepository<Dbo, PrimaryType> {
    suspend fun clearIfNotIn(ids: List<@JvmSuppressWildcards PrimaryType>)
    fun getSelectedItems(ids: List<@JvmSuppressWildcards PrimaryType>): Flow<List<@JvmSuppressWildcards Dbo>>?
}