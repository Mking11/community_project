package com.mking11.community_project.common.utils.use_case_helper.user_cases

import com.madtechet.musica.common.firebase.Dto
import com.mking11.community_project.common.utils.use_case_helper.RepositoryCommon
import com.mking11.community_project.common.room.Dbo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetItem<dbo: Dbo, dto: Dto, PrimaryType, T : RepositoryCommon<dbo, dto, PrimaryType>>(private val repository: T) {

    operator fun invoke(id: PrimaryType): Flow<dbo?> = flow {
        emit(repository.getItem(id))
    }
}

