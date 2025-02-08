package com.example.onlyoffice.models

data class DocumentsResponse(
    val response: Documents? = null
)

data class Documents(
    val files: List<File>,
    val folders: List<Folder>
)

data class File(
    val id: Int,
    val folderId: Int,
    val title: String
)

data class Folder(
    val id: Int,
    val title: String
)