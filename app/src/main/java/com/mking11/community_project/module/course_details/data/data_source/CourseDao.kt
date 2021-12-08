package com.mking11.community_project.module.course_details.data.data_source;

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.madtechet.musica.common.room.room_helper.contracts.IDaoEssentialsRepository
import com.mking11.community_project.module.course_details.domain.model.CourseDetailsDbo
import com.mking11.community_project.module.course_details.domain.model.ICourseDetails
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CourseDao : IDaoEssentialsRepository<CourseDetailsDbo, String> {

    @Query("Select * From Course where id=:id")
    abstract suspend fun getItem(id: String): CourseDetailsDbo?

    @Query("Select * From  Course ")
    abstract fun getItems(): Flow<List<CourseDetailsDbo>>

    @Query("Select * From Course where title Like :search OR published_title Like :search  OR headline like :search order by title")
    abstract fun getCourses(search: String): PagingSource<Int, CourseDetailsDbo>

    @Query("Delete from Course ")
    abstract suspend fun clear()

    @Query("Select *From course ")
    abstract fun getCourses(): PagingSource<Int, CourseDetailsDbo>


}
