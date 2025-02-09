package com.example.onlyoffice.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlyoffice.interfaces.ApiService
import com.example.onlyoffice.models.DocumentsResponse
import com.example.onlyoffice.objects.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DocumentViewModel: ViewModel() {
    private lateinit var apiService: ApiService
    private val _documents = MutableLiveData<DocumentsResponse?>(null)
    val documents: LiveData<DocumentsResponse?> get() = _documents

    fun initPortal(portal: String) {
        println("Подключение к порталу: $portal")
        apiService = RetrofitClient.getClient(portal).create(ApiService::class.java)
    }

    fun getDocuments() {
        viewModelScope.launch {
            try {
                val response = apiService.getDocuments()
                _documents.value = response
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

    fun getFolderById(id: Int) {
        viewModelScope.launch {
            try {
                val response = apiService.getFolderById(id)
                _documents.value = response
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