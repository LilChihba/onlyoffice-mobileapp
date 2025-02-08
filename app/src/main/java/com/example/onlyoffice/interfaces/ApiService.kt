package com.example.onlyoffice.interfaces

import com.example.onlyoffice.models.AuthResponse
import com.example.onlyoffice.models.DocumentsResponse
import com.example.onlyoffice.models.MyProfileResponse
import com.example.onlyoffice.models.RoomResponse
import com.example.onlyoffice.models.TrashResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("api/2.0/authentication")
    suspend fun authentication(@Body requestBody: LoginRequest): AuthResponse

    @POST("api/2.0/authentication/logout")
    suspend fun logout(): Response

    @GET("api/2.0/files/@my")
    suspend fun getDocuments(): DocumentsResponse

    @GET("api/2.0/people/@self")
    suspend fun getMyProfile(): MyProfileResponse

    @GET("api/2.0/files/rooms")
    suspend fun getRooms(): RoomResponse

    @GET("api/2.0/files/@trash")
    suspend fun getTrash(): TrashResponse
}

data class Response(
    val status: Int,
    val statusCode: Int,
)

data class LoginRequest(
    val username: String,
    val password: String
)