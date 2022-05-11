package com.example.myexpensecalculator.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myexpensecalculator.dao.ExpenseDao;
import com.example.myexpensecalculator.database.AppDatabase;
import com.example.myexpensecalculator.entities.Expense;

import java.util.List;

public class ExpenseRepository {
    private ExpenseDao expenseDao;

    public ExpenseRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getDatabaseInstance(application);
        expenseDao = appDatabase.expenseDao();
    }

    public ExpenseRepository() {
    }

    public void insertExpense(Expense expense){
        expenseDao.insert(expense);
    }

    public void updateExpense(Expense expense){
        expenseDao.update(expense);
    }

    public void deleteExpense(Expense expense){
        expenseDao.delete(expense);
    }

    public LiveData<List<Expense>> fetchExpenseList(){
        return expenseDao.getAllExpenses();
    }

    public int getTotal(List<Expense> expenses) {
        int total=0;
        for (Expense expense :expenses) {
            total+=Integer.parseInt(expense.getAmount());
        }
        return total;
    }
}
