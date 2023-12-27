package com.andricohalim.suitmediatest.ui.thirdscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.andricohalim.suitmediatest.repository.UserRepository
import com.andricohalim.suitmediatest.response.DataItem

class ThirdScreenViewModel(private val repository: UserRepository) : ViewModel() {

    val listUser: LiveData<PagingData<DataItem>> = repository.getUser().cachedIn(viewModelScope)
}