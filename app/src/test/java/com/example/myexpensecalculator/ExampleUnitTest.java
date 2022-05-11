package com.example.myexpensecalculator;

import com.example.myexpensecalculator.entities.Expense;
import com.example.myexpensecalculator.repositories.ExpenseRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    List<Expense> expenses;

    ExpenseRepository expenseRepository;

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Before
    public void setup(){
        expenses = new ArrayList<>();
        expenseRepository = new ExpenseRepository();
    }

    @Test
    public void getTotal(){
        Expense expense = new Expense();
        expense.setAmount("20");
        expenses.add(expense);
        assertEquals("Shud work",2,expenseRepository.getTotal(expenses));
    }
}