package com.mking11.community_project.common.utils.use_case_helper

interface SharedUseCases <T>{
    suspend operator fun invoke(ids:List<String>,repository: T){

    }
}