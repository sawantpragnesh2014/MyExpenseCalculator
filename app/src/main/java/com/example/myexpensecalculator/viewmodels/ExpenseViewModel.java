    package com.example.myexpensecalculator.viewmodels;

import android.app.Application;
import android.app.DatePickerDialog;
import android.util.Log;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myexpensecalculator.database.AppDatabase;
import com.example.myexpensecalculator.entities.Expense;
import com.example.myexpensecalculator.repositories.ExpenseRepository;
import com.example.myexpensecalculator.repositories.ExpenseRepositoryK;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {
    private Calendar calendar;
    /*private MutableLiveData<List<Expense>> mExpenseList;*/
    private final LiveData<List<Expense>> mExpenseList;
    private final ExpenseRepositoryK expenseRepository;


    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        calendar = Calendar.getInstance();
        /*mExpenseList = new MutableLiveData<>();*/
        expenseRepository = new ExpenseRepositoryK(application);
        mExpenseList = expenseRepository.fetchExpenseList();
    }

    public void insertExpense(Expense expense){
        expenseRepository.insertExpense(expense);
    }

    public void updateExpense(Expense expense){
        expenseRepository.updateExpense(expense);
    }

    public void deleteExpense(Expense expense){
        expenseRepository.delete(expense);
    }

    /*public void fetchAllExpenses(){
        mExpenseList.setValue(expenseRepository.fetchExpenseList());
    }*/

    public LiveData<List<Expense>> getmExpenseList() {
        return mExpenseList;
    }

    /*public MutableLiveData<List<Expense>> getmExpenseList() {
        return mExpenseList;
    }*/

    /*public LiveData<List<Expense>> fetchAllExpensesInRange(Date dateFrom, Date dateTo){
        return appDatabase.expenseDao().getAllExpensesInRange(dateFrom,dateTo);
    }*/

    public int getTotal(List<Expense> expenses) {
        return expenseRepository.getTotal(expenses);
    }
}
