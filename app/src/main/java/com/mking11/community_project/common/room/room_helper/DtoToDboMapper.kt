package com.madtechet.musica.common.room.room_helper

abstract class DtoToDboMapper<Dto,Dbo> {
    abstract fun toDto():Dto
    abstract fun toDbo():Dbo
}