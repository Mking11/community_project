package com.mking11.community_project.common.room.room_helper.repositories

import com.mking11.community_project.common.room.room_helper.contracts.IDaoEssentialsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface DaoEssentialsRepositoryImpl<OutPutType : Any> {

    fun insertOrUpdate(
        item: OutPutType?, scope: CoroutineScope,
        handler: CoroutineExceptionHandler,
        dao: IDaoEssentialsRepository<OutPutType>
    ) = scope.launch(handler) {
        if (item != null) {
            dao.insertOrUpdate(item)
        }
    }

    fun deleteItem(
        item: OutPutType?,
        scope: CoroutineScope,
        handler: CoroutineExceptionHandler,
        dao: IDaoEssentialsRepository<OutPutType>
    ) = scope.launch(handler) {
        if (item != null) {
            val result = dao.deleteItem(item)

        }
    }
}