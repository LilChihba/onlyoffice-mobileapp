package com.example.onlyoffice.models

import java.time.LocalDateTime

data class Token(
    val token: String,
    val expires: LocalDateTime,
    val sms: Boolean,
    val tfa: Boolean
)