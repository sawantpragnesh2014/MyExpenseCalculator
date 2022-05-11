package com.example.myexpensecalculator.app;

import android.app.Application;

import com.example.myexpensecalculator.database.AppDatabase;
import com.example.myexpensecalculator.repositories.ExpenseRepository;

public class MyExpenseCalculatorApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase.getDatabaseInstance(this);
    }


}
