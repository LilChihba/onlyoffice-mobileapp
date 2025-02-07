package com.example.onlyoffice.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlyoffice.interfaces.ApiService
import com.example.onlyoffice.interfaces.LoginRequest
import com.example.onlyoffice.models.AuthResponse
import com.example.onlyoffice.objects.RetrofitClient
import com.example.onlyoffice.objects.URL
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class TokenViewModel: ViewModel() {
    private lateinit var apiService: ApiService
    private val _token = MutableLiveData<AuthResponse?>(null)
    val token: LiveData<AuthResponse?> get() = _token

    fun initPortal(portal: String) {
        println("Подключение к порталу: $portal")
        apiService = RetrofitClient.getClient(portal).create(ApiService::class.java)
        URL.link = portal
    }

    fun authentication(request: LoginRequest, onResult: (AuthResponse?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.authentication(request)
                _token.value = response
                onResult(response)
            } catch (e: HttpException) {
                when(e.code()) {
                    400 -> println("${e.code()}: Bad Request")
                    401 -> println("${e.code()}: Unauthorized")
                    404 -> println("${e.code()}: Not Found")
                    500 -> println("${e.code()}: Internal Server Error")
                    else -> println("${e.code()}")
                }
                onResult(null)
            } catch (e: IOException) {
                println("Network error: ${e.message}")
                onResult(null)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                apiService.logout()
                _token.value = null
                URL.link = ""

            } catch (e: HttpException) {
                when(e.code()) {
                    400 -> println("${e.code()}: Bad Request")
                    401 -> println("${e.code()}: Unauthorized")
                    404 -> println("${e.code()}: Not Found")
                    500 -> println("${e.code()}: Internal Server Error")
                    else -> println("${e.code()}")
                }
            } catch (e: IOException) {
                println("Network error: ${e.message}")
            }
        }
    }
}