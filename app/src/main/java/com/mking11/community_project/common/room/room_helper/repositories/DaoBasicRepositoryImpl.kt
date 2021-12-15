package com.mking11.community_project.common.room.room_helper.repositories

import com.madtechet.musica.common.room.room_helper.contracts.IDaoBasicRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

interface DaoBasicRepositoryImpl<OutPutType : Any, PrimaryKeyType> :
    DaoEssentialsRepositoryImpl<OutPutType> {
    fun clearTable(
        scope: CoroutineScope,
        handler: CoroutineExceptionHandler,
        dao: IDaoBasicRepository<OutPutType, PrimaryKeyType>
    ) =
        scope.launch(handler) {
            dao.clear()
        }

    fun getItems(  dao: IDaoBasicRepository<OutPutType, PrimaryKeyType>): Flow<List<@JvmSuppressWildcards OutPutType>>? = dao.getItems()


    suspend fun getItem(id: PrimaryKeyType,  dao: IDaoBasicRepository<OutPutType, PrimaryKeyType>) = dao.getItem(id)

}