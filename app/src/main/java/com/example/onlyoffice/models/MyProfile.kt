package com.example.onlyoffice.models

data class MyProfileResponse(
    val response: MyProfile? = null
)

data class MyProfile(
    val userName: String,
    val email: String,
    val avatar: String,
)