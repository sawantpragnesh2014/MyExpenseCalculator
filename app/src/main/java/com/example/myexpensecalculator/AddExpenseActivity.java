package com.example.myexpensecalculator;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.example.myexpensecalculator.entities.Expense;
import com.example.myexpensecalculator.viewmodels.ExpenseViewModel;
import com.example.myexpensecalculator.viewmodels.ExpenseViewModelK;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddExpenseActivity extends BaseActivityK {
    private EditText edtExpenseName;
    private EditText edtExpenseAmount;
    private TextView txtDate;
    private Button btnSave;
    private SimpleDateFormat sdf;
    private Calendar calendar;
    private ExpenseViewModelK expenseViewModel;
    private ImageButton imgFood;
    private TextView txtFood;
    private ImageButton imgEntmnt;
    private TextView txtEntmnt;
    private ImageButton imgTravel;
    private TextView txtTravel;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AddExpenseActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        init();
    }

    @Override
    public void init() {
        expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModelK.class);
        edtExpenseName = findViewById(R.id.edt_name);
        edtExpenseAmount = findViewById(R.id.edt_amount);
        txtDate = findViewById(R.id.txt_date);
        sdf = new SimpleDateFormat("dd MMM YYYY");
        calendar = Calendar.getInstance();
        txtDate.setText(sdf.format(calendar.getTime()));
        btnSave = findViewById(R.id.btn_save);

        imgEntmnt = findViewById(R.id.img_entertainment);
        imgFood = findViewById(R.id.img_food);
        imgTravel = findViewById(R.id.img_travel);

        txtEntmnt = findViewById(R.id.txt_entertainment);
        txtFood = findViewById(R.id.txt_food);
        txtTravel = findViewById(R.id.txt_travel);

        btnSave.setOnClickListener(this);
        txtDate.setOnClickListener(this);
        imgEntmnt.setOnClickListener(this);
        imgFood.setOnClickListener(this);
        imgTravel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_date:
                showDatePicker();
                break;

            case R.id.btn_save:
                insertExpenseData();
                finish();
                break;
            case R.id.img_food:
                txtFood.setTextColor(getResources().getColor(R.color.color3F9AC8));
                break;
            case R.id.img_entertainment:
                txtEntmnt.setTextColor(getResources().getColor(R.color.color3F9AC8));
                break;

            case R.id.img_travel:
                txtTravel.setTextColor(getResources().getColor(R.color.color3F9AC8));
                break;
        }
    }

    private void insertExpenseData() {
        Expense expense = new Expense();
        expense.setExpenseName(edtExpenseName.getText().toString());
        expense.setAmount(edtExpenseAmount.getText().toString());
        Date date = new Date();
        date.setTime(calendar.getTimeInMillis());
        expense.setDate(date);
        expenseViewModel.insertExpense(expense);
        Toast.makeText(this, "Expense inserted", Toast.LENGTH_SHORT).show();
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
                txtDate.setText(sdf.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
}
