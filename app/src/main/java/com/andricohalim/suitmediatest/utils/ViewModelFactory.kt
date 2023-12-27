package com.andricohalim.suitmediatest.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andricohalim.suitmediatest.injection.Injection
import com.andricohalim.suitmediatest.repository.UserRepository
import com.andricohalim.suitmediatest.ui.thirdscreen.ThirdScreenViewModel

class ViewModelFactory private constructor(
    private val repository: UserRepository,
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)) {
            return ThirdScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("No ModelClass: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory {
            return ViewModelFactory(Injection.provideRepository(context))
        }
    }
}