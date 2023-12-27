package com.andricohalim.suitmediatest.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.andricohalim.suitmediatest.data.UserPagingSource
import com.andricohalim.suitmediatest.response.DataItem
import com.andricohalim.suitmediatest.retrofit.ApiService

class UserRepository(
    private val apiService: ApiService
) {
    fun getUser(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 12
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }
}