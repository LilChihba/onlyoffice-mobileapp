package com.example.onlyoffice.interfaces

import com.example.onlyoffice.models.AuthResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApiService {
    @Headers("Content-Type: application/json")
    @POST("api/2.0/authentication")
    suspend fun authentication(@Body requestBody: LoginRequest): AuthResponse
}

data class LoginRequest(
    val username: String,
    val password: String
)