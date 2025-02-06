package com.example.onlyoffice.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlyoffice.interfaces.ApiService
import com.example.onlyoffice.models.MyProfileResponse
import com.example.onlyoffice.objects.RetrofitClient
import com.example.onlyoffice.objects.URL
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MyProfileViewModel: ViewModel() {
    private lateinit var apiService: ApiService
    private val _profile = MutableLiveData<MyProfileResponse?>(null)
    val profile: LiveData<MyProfileResponse?> get() = _profile

    fun initPortal() {
        println("Подключение к порталу: ${URL.link}")
        apiService = RetrofitClient.getClient(URL.link).create(ApiService::class.java)
    }

    fun getProfile() {
        viewModelScope.launch {
            try {
                val response = apiService.getMyProfile()
                _profile.value = response
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