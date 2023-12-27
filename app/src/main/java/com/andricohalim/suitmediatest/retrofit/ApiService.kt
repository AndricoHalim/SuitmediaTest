package com.andricohalim.suitmediatest.retrofit

import com.andricohalim.suitmediatest.response.DataItem
import com.andricohalim.suitmediatest.response.UserResponse
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUser(): List<DataItem>
}