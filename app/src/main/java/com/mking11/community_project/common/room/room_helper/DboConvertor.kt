package com.madtechet.musica.common.room.room_helper


interface DboConvertor<T> {
    fun toDbo(key: String? = null): T? {
        return null
    }
}