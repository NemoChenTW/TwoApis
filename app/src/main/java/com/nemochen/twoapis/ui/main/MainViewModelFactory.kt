package com.nemochen.twoapis.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nemochen.twoapis.model.StatusRepository
import java.lang.IllegalStateException

class MainViewModelFactory(private val statusRepository: StatusRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(statusRepository) as T
        }
        throw IllegalStateException("Unknown viewModel class")
    }
}