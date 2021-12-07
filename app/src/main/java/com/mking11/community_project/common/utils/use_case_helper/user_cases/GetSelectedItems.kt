package com.mking11.community_project.common.utils.use_case_helper.user_cases

import com.madtechet.musica.common.firebase.Dto
import com.mking11.community_project.common.room.Dbo
import com.mking11.community_project.common.utils.use_case_helper.RepositoryCommon
import kotlinx.coroutines.flow.Flow

class GetSelectedItems<dbo: Dbo, dto: Dto, PrimaryType, T : RepositoryCommon<dbo, dto, PrimaryType>>(private val repository: T) {

    operator fun invoke(ids: List<PrimaryType>): Flow<List<dbo>>? {
        return repository.getSelectedItems(ids)
    }
}