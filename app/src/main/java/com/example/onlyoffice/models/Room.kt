package com.example.onlyoffice.models

data class RoomResponse(
    val response: Room? = null
)

data class Room(
    val folders: List<Folder>
)

