package com.example.myexpensecalculator.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.myexpensecalculator.dao.ExpenseDao
import com.example.myexpensecalculator.database.AppDatabase
import com.example.myexpensecalculator.entities.Expense

class ExpenseRepositoryK(application: Application) {

    private val expenseDao: ExpenseDao = AppDatabase.getDatabaseInstance(application).expenseDao();

    fun insertExpense(expense : Expense){
        expenseDao.insert(expense)
    }

    fun updateExpense(expense : Expense){
        expenseDao.update(expense)
    }

    fun delete(expense : Expense){
        expenseDao.delete(expense)
    }

    fun fetchExpenseList(): LiveData<List<Expense>> {
        return expenseDao.allExpenses;
    }

    fun getTotal(expenses : List<Expense>): Int {
        val total = 0;
        for (expense in expenses) {
            total.plus(Integer.parseInt(expense.amount));
        }
        return total;
    }


}