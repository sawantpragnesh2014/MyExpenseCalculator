package com.example.myexpensecalculator.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myexpensecalculator.entities.Expense
import com.example.myexpensecalculator.repositories.ExpenseRepositoryK

class ExpenseViewModelK(application:Application): AndroidViewModel(application) {
    private val mExpenseList: LiveData<List<Expense>>
    private val expenseRepositoryK = ExpenseRepositoryK(application)

    init {
        mExpenseList = expenseRepositoryK.fetchExpenseList()
    }

    fun insertExpense(expense : Expense){
        expenseRepositoryK.insertExpense(expense)
    }

    fun getTotal(expenseList : List<Expense>) : Int{
        return expenseRepositoryK.getTotal(expenseList)
    }

}