package com.example.onlyoffice.models

data class Token(
    val token: String,
    val expires: String
)

data class AuthResponse(
    val response: Token,
    val statusCode: Int
)