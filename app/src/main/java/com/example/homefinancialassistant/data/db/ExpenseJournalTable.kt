package com.example.homefinancialassistant.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.data.dao.ExpenseJournalDao
import com.example.homefinancialassistant.data.db.converters.Converters

@Database(entities = [Consumption::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ExpenseJournalTable: RoomDatabase() {
    abstract fun expenseJournalDao(): ExpenseJournalDao
}