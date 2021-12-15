package com.mking11.community_project.module.course_details.data.data_source;

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.mking11.community_project.common.room.room_helper.contracts.IDaoEssentialsRepository
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CourseDao : IDaoEssentialsRepository<CourseDetailsDbo> {

    @Query("Select * From Course where id=:id")
    abstract suspend fun getItem(id: String): CourseDetailsDbo?

    @Query("Select * From  Course ")
    abstract fun getItems(): Flow<List<CourseDetailsDbo>>

    @Query("Select * From Course where title Like :search OR published_title Like :search  OR headline like :search order by title")
    abstract fun getCourses(search: String): PagingSource<Int, CourseDetailsDbo>

    @Query("Delete from Course ")
    abstract suspend fun clear()

    @Query("Select *From course where subcategory like :subcategory or search like :search order by timeStamp")
    abstract fun getCourses(
        search: String?,
        subcategory: String?
    ): PagingSource<Int, CourseDetailsDbo>


}
