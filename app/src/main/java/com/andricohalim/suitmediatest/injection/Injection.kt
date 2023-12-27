package com.andricohalim.suitmediatest.injection

import android.content.Context
import com.andricohalim.suitmediatest.repository.UserRepository
import com.andricohalim.suitmediatest.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService)
    }
}