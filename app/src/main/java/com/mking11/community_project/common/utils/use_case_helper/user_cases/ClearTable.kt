package com.mking11.community_project.common.utils.use_case_helper.user_cases

import com.madtechet.musica.common.firebase.Dto
import com.mking11.community_project.common.room.Dbo
import com.mking11.community_project.common.utils.use_case_helper.RepositoryCommon

class ClearTable<dbo: Dbo, dto: Dto, PrimaryType, T : RepositoryCommon<dbo, dto, PrimaryType>>(private val repository: T) {
    suspend operator fun invoke() {
        repository.clearTable()
    }
}