package com.example.homefinancialassistant.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "rates_table", indices = [Index(value = ["currency"], unique = true)])
data class RateCurrency(
    @PrimaryKey
    val currency: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "rate")
    val rate: Double
)