package com.example.myexpensecalculator.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ExpenseViewModelFactoryK(private val application: Application) : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ExpenseViewModel(application) as T
    }
}