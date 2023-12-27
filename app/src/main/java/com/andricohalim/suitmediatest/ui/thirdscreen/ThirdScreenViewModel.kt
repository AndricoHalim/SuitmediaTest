package com.andricohalim.suitmediatest.ui.thirdscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.andricohalim.suitmediatest.repository.UserRepository
import com.andricohalim.suitmediatest.response.DataItem
import com.andricohalim.suitmediatest.response.UserResponse
import com.andricohalim.suitmediatest.utils.Result
import kotlinx.coroutines.launch

class ThirdScreenViewModel(private val repository: UserRepository) : ViewModel() {

    val listUser: LiveData<PagingData<DataItem>> = repository.getUser().cachedIn(viewModelScope)
}