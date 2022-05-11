package com.example.myexpensecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myexpensecalculator.adapters.ExpenseAdapter;
import com.example.myexpensecalculator.entities.Expense;
import com.example.myexpensecalculator.viewmodels.ExpenseViewModel;
import com.example.myexpensecalculator.viewmodels.ExpenseViewModelFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final static String TAG = "MainActivity";
    private TextView txtDatePicker;
    private TextView txtMonth;
    private TextView txtBalance;
    private TextView txtTotalExpense;
    private TextView txtRemaining;
    private Calendar calendar = Calendar.getInstance();
    private DateFormat sdf;
    private ExpenseViewModel expenseViewModel;
    private ExpenseAdapter expenseAdapter;
    private Button btnAdd;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        txtDatePicker = findViewById(R.id.txt_date_picker);
        btnAdd = findViewById(R.id.btn_add);
        txtMonth = findViewById(R.id.txt_month);
        txtBalance = findViewById(R.id.txt_budget);
        txtTotalExpense = findViewById(R.id.txt_total2);
        txtRemaining = findViewById(R.id.txt_remaining2);
        recyclerView = findViewById(R.id.rv_expense);

//        expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);
        expenseViewModel = ViewModelProviders.of(this, new ExpenseViewModelFactory(getApplication())).get(ExpenseViewModel.class);

        expenseAdapter = new ExpenseAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(expenseAdapter);
        Date date = new Date();
        date.setTime(calendar.getTimeInMillis());
        expenseViewModel.getmExpenseList().observe(this,this::getAllExpenses);

        sdf = new SimpleDateFormat();
        calendar = Calendar.getInstance();
        txtDatePicker.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        /*insertExpenseData();
        insertExpenseData();
        insertExpenseData();
        insertExpenseData();
        findViewById(R.id.btn_hit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseViewModel.fetchAllExpenses();
            }
        });

        findViewById(R.id.btn_remove_observer).setOnClickListener(this);

        findViewById(R.id.btn_set_observer).setOnClickListener(this);*/
    }

    private void insertExpenseData() {
        Expense expense = new Expense();
        expense.setExpenseName("Abc");
        expense.setAmount("230");
        Date date = new Date();
        date.setTime(calendar.getTimeInMillis());
        expense.setDate(date);
        expenseViewModel.insertExpense(expense);
//        Toast.makeText(this, "Expense inserted", Toast.LENGTH_SHORT).show();
    }

    private void setList(List<Expense> expenses) {
        Log.d(TAG, "setList: ");
    }

    private void getAllExpenses(List<Expense> expenses) {
        Log.d(TAG, "getAllExpenses: "+expenses.size());
        expenseAdapter.addData(expenses);
        setTotalAndRemainingData(expenses);
    }

    private void setTotalAndRemainingData(List<Expense> expenses) {
        int total = expenseViewModel.getTotal(expenses);
        txtTotalExpense.setText(String.valueOf(total));

        int remaining = Integer.parseInt(txtBalance.getText().toString())-total;
        txtRemaining.setText(String.valueOf(remaining));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.txt_date_picker:
                showDatePicker();
                break;

                case R.id.btn_add:
                callAddExpenseActivity();
                break;
                case R.id.btn_set_observer:
                    expenseViewModel.getmExpenseList().observe(this,this::setList);
                break;
                case R.id.btn_remove_observer:
                    expenseViewModel.getmExpenseList().removeObservers(this);
                break;
        }
    }

    private void callAddExpenseActivity() {
        startActivity(AddExpenseActivity.getStartIntent(this));

    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Log.i("DeviceDetail", "OnDateSet");
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.getTimeInMillis();
                txtDatePicker.setText(sdf.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
}
