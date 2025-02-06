package com.example.onlyoffice.interfaces

import com.example.onlyoffice.models.AuthResponse
import com.example.onlyoffice.models.MyProfileResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("api/2.0/authentication")
    suspend fun authentication(@Body requestBody: LoginRequest): AuthResponse
//
//    @GET("api/2.0/files/@my")
//    suspend fun getDocuments():

    @GET("api/2.0/people/@self")
    suspend fun getMyProfile(): MyProfileResponse
}

data class LoginRequest(
    val username: String,
    val password: String
)