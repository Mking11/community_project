package com.mking11.community_project.common.room.room_helper

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel


class ScopeShared<T>(
    private val childClass: Class<T>,
//    private val firebaseCrash: FirebaseCrash,
    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO),
) {
    val handler = CoroutineExceptionHandler { _, e ->
//        firebaseCrash.setErrorToFireBase(e, " ${childClass.canonicalName}  ${childClass.name} : ")
    }

    fun closeRepo() {
        try {
            scope.cancel()
        } catch (e: Exception) {
//            firebaseCrash.setErrorToFireBase(e, "${childClass.name} .kt : ")
        }
    }

}