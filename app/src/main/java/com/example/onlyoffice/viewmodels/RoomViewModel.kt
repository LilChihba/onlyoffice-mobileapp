package com.example.onlyoffice.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlyoffice.interfaces.ApiService
import com.example.onlyoffice.models.RoomResponse
import com.example.onlyoffice.objects.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RoomViewModel: ViewModel() {
    private lateinit var apiService: ApiService
    private val _rooms = MutableLiveData<RoomResponse?>(null)
    val rooms: LiveData<RoomResponse?> get() = _rooms

    fun initPortal(portal: String) {
        println("Подключение к порталу: $portal")
        apiService = RetrofitClient.getClient(portal).create(ApiService::class.java)
    }

    fun getRooms() {
        viewModelScope.launch {
            try {
                val response = apiService.getRooms()
                _rooms.value = response
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