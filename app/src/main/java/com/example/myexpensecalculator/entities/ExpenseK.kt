package com.example.myexpensecalculator.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity
data class ExpenseK (
        @PrimaryKey(autoGenerate = true) val id:Int,
        @ColumnInfo(name = "expense_name")
        val  expenseName: String,
        val amount: String,
        @TypeConverters({TimestampConverter.class})
        val date: Date
)