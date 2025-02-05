package com.example.onlyoffice.models

data class Token(
    val token: String,
    val expires: String,
    val sms: Boolean,
    val tfa: Boolean
)

data class Link(
    val href: String,
    val action: String
)

data class AuthResponse(
    val response: Token,
    val count: Int,
    val links: List<Link>,
    val status: Int,
    val statusCode: Int
)