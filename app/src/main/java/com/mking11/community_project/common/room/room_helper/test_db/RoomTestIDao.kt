package com.madtechet.musica.common.room.room_helper.test_db

//@Dao
//abstract class RoomTestIDao() : IDaoCommon<TestObjectDb, String> {
//
//
//    @Query("Delete from TestTable")
//    abstract override suspend fun clear()
//
//    @Query("Delete from TestTable where id not in (:ids)")
//    abstract override suspend fun clearIfNotIn(ids: List<String>)
//
//    @Query("Select * from TestTable ")
//    abstract override fun getItems(): Flow<List<TestObjectDb>>?
//
//    @Query("Select * from TestTable where id in(:ids)")
//    abstract override fun getSelectedItems(ids: List<String>): Flow<List<TestObjectDb>>?
//
//    @Query("Select * from TestTable where id=:id")
//    abstract override suspend fun getItem(id: String): TestObjectDb?
//}