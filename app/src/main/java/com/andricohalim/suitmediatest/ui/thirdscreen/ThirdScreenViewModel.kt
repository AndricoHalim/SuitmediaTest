package com.andricohalim.suitmediatest.ui.thirdscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.andricohalim.suitmediatest.repository.UserRepository
import com.andricohalim.suitmediatest.response.UserResponse
import com.andricohalim.suitmediatest.utils.Result
import kotlinx.coroutines.launch

class ThirdScreenViewModel(private val repository: UserRepository) : ViewModel() {

    private val _listUser = MutableLiveData<Result<UserResponse>>()
    val listUser: LiveData<Result<UserResponse>> = _listUser

    init {
        getUserList()
    }

    private fun getUserList() {
        viewModelScope.launch {
            val response = repository.getUSer()
            response.asFlow().collect {
                _listUser.value = it
            }
        }
    }
}