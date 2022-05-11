package com.example.myexpensecalculator.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myexpensecalculator.R;
import com.example.myexpensecalculator.entities.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    List<Expense> expenseList;
    private final static String TAG = "ExpenseAdapter";


    public ExpenseAdapter() {
        this.expenseList = new ArrayList<>();
    }

    public void addData(List<Expense> expenseList) {
        this.expenseList.clear();
        this.expenseList.addAll(expenseList);
        Log.d(TAG, "addData: "+expenseList.size());
        notifyDataSetChanged();
    }

    public void clearData() {
        if (expenseList.isEmpty()) {
            return;
        }
        expenseList.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_expense_list, parent, false);
        return new ExpenseAdapter.ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {
        private TextView txtExpenseName;
        private TextView txtExpenseAmount;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d(TAG, "ExpenseViewHolder: ");
            txtExpenseName = itemView.findViewById(R.id.txt_expense_name);
            txtExpenseAmount = itemView.findViewById(R.id.txt_expense_amount);
        }

        public void bind() {
            Log.d(TAG, "bind: ");
            Expense expense = expenseList.get(getAdapterPosition());
            txtExpenseName.setText(expense.getExpenseName());
            txtExpenseAmount.setText(expense.getAmount());
        }
    }
}
