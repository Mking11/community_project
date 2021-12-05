package com.madtechet.musica.common.room.room_helper.contracts

import androidx.room.*


interface IDaoEssentialsRepository<Dbo, PrimaryType> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItem(item: Dbo): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(item: Dbo)

    @Delete
    suspend fun deleteItem(item: Dbo):Int?

    @Transaction
    fun insertOrUpdate(item: Dbo) {
        val result = insertItem(item)
        if (result == -1L) update(item)
    }


}


