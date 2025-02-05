package com.example.onlyoffice.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlyoffice.interfaces.AuthApiService
import com.example.onlyoffice.interfaces.LoginRequest
import com.example.onlyoffice.models.AuthResponse
import com.example.onlyoffice.objects.RetrofitClient
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    private lateinit var authApi: AuthApiService

    fun initPortal(portal: String) {
        authApi = RetrofitClient.getClient(portal).create(AuthApiService::class.java)
    }

    fun authentication(request: LoginRequest, onResult: (AuthResponse?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = authApi.authentication(request)
                onResult(response)
            } catch (e: Exception) {
                println("Login failed: ${e.message}")
                onResult(null)
            }
        }
    }
}