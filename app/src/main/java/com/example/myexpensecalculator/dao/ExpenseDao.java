package com.example.myexpensecalculator.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myexpensecalculator.entities.Expense;

import java.util.Date;
import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    void insert(Expense expense);

    @Update
    void update(Expense expense);

    @Delete
    void delete(Expense expense);

    @Query("Select * from expense")
    LiveData<List<Expense>> getAllExpenses();

    /*@Query("Select * from EXPENSE where date between :dateFrom AND :dateTo")
    LiveData<List<Expense>> getAllExpensesInRange(Date dateFrom, Date dateTo);*/
}
