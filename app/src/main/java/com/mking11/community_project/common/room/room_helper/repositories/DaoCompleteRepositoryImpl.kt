package com.madtechet.musica.common.room.room_helper.repositories

import com.madtechet.musica.common.room.room_helper.contracts.IDaoCompleteRepository
import com.mking11.community_project.common.room.room_helper.repositories.DaoBasicRepositoryImpl
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

interface DaoCompleteRepositoryImpl<OutPutType : Any, PrimaryKeyType> :
    DaoBasicRepositoryImpl<OutPutType, PrimaryKeyType> {

    fun clearItems(
        ids: List<PrimaryKeyType>, scope: CoroutineScope,
        handler: CoroutineExceptionHandler,
        dao: IDaoCompleteRepository<OutPutType, PrimaryKeyType>
    ) = scope.launch(handler) {
        dao.clearIfNotIn(ids)
    }

    fun getItems(
        items: List<PrimaryKeyType>,
        dao: IDaoCompleteRepository<OutPutType, PrimaryKeyType>
    ): Flow<List<OutPutType>>? =
        dao.getSelectedItems(items)


}