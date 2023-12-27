package com.andricohalim.suitmediatest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.andricohalim.suitmediatest.data.UserPagingSource
import com.andricohalim.suitmediatest.response.DataItem
import com.andricohalim.suitmediatest.response.UserResponse
import com.andricohalim.suitmediatest.retrofit.ApiService
import com.andricohalim.suitmediatest.utils.Result
import java.lang.Exception

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