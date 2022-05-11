package com.example.myexpensecalculator.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myexpensecalculator.dao.ExpenseDao;
import com.example.myexpensecalculator.entities.Expense;

@Database(entities = {Expense.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase sInstance;
    private static final String DATABASE_NAME = "expensecalculator";

    // Get a database instance
    public static synchronized AppDatabase getDatabaseInstance(Context context) {
        if (sInstance == null) {
            sInstance = create(context);
        }
        return sInstance;
    }

    // Create the database
    static AppDatabase create(Context context) {
        RoomDatabase.Builder<AppDatabase> builder = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class,
                DATABASE_NAME)
                .allowMainThreadQueries();
        return builder.build();
    }

    public abstract ExpenseDao expenseDao();


}
