package com.example.homefinancialassistant.data

import androidx.room.ColumnInfo

data class Category(
    @ColumnInfo(name = "category") val category: String
)