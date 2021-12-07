package com.mking11.community_project.common.room.room_helper

import android.util.Log
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
        Log.e("ScopeCoroutineException", "${childClass.name}.kt: ")
    }

    fun closeRepo() {
        try {
            scope.cancel()
        } catch (e: Exception) {
            Log.e("ScopeError", "${childClass.name} .kt : ")
        }
    }

}