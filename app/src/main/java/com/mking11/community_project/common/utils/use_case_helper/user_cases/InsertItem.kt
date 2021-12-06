package com.mking11.community_project.common.utils.use_case_helper.user_cases

import com.madtechet.musica.common.firebase.Dto
import com.mking11.community_project.common.room.Dbo
import com.mking11.community_project.common.utils.use_case_helper.RepositoryCommon

abstract class InsertItem<dbo: Dbo, dto: Dto, PrimaryType, T : RepositoryCommon<dbo, dto, PrimaryType>>(
    private val repository: T
) {
operator fun invoke(dtoModel: dto) {
        repository.insertItem(dtoModel)
        onInserted(dtoModel)
    }

    abstract fun onInserted(dtoModel: dto)


}