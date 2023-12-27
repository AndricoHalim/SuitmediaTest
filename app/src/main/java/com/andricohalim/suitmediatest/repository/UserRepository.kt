package com.andricohalim.suitmediatest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.andricohalim.suitmediatest.response.DataItem
import com.andricohalim.suitmediatest.retrofit.ApiService
import com.andricohalim.suitmediatest.utils.Result
import java.lang.Exception

class UserRepository(
    private val apiService: ApiService
) {

    fun getUSer(): LiveData<Result<List<DataItem>>> =
        liveData{
            emit(Result.Loading)
            try {
                val userResponse = apiService.getUser()
                emit(Result.Success(userResponse))
            } catch (e: Exception){
                e.printStackTrace()
                emit(Result.Error("Terjadi kesalahan: ${e.message}"))
            }
        }
}