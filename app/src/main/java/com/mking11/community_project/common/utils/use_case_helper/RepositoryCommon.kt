package com.mking11.community_project.common.utils.use_case_helper

import androidx.lifecycle.LiveData
import com.madtechet.musica.common.firebase.Dto
import com.mking11.community_project.common.room.Dbo
import kotlinx.coroutines.flow.Flow

interface RepositoryCommon<dbo : Dbo, dto : Dto, Primary> {
    fun closeRepository()
//    fun getItemRemote(callback: RepoCallback<dto>)
//    fun getItemRemote(): LiveData<HashMap<Primary, dto>>?
    fun insertItem(item: dto)
    fun deleteItem(item: dto)
    suspend fun getItem(id: Primary): dbo?
    suspend fun clearNotSelected(list: List<Primary>)
    suspend fun clearTable()
    fun getAllItems(): Flow<List<dbo>>?
    fun getSelectedItems(ids: List<Primary>): Flow<List<dbo>>?
}