package com.example.onlyoffice.models

data class TrashResponse(
    val response: Trash? = null
)

data class Trash(
    val files: List<File>,
    val folders: List<Folder>
)