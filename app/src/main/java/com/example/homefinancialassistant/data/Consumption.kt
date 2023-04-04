package com.example.homefinancialassistant.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "expense_journal")
data class Consumption(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "date")
    val date: Calendar,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "price")
    val price: Double,
    @ColumnInfo(name = "description")
    val description: String
    )