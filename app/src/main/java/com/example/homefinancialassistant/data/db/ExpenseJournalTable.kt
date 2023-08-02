package com.example.homefinancialassistant.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.homefinancialassistant.data.CategoryConsumption
import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.data.dao.ExpenseJournalDao
import com.example.homefinancialassistant.data.db.converters.Converters

@Database(entities = [Consumption::class, CategoryConsumption::class], version = 2, autoMigrations = [AutoMigration(from = 1, to = 2)], exportSchema = true)
@TypeConverters(Converters::class)
abstract class ExpenseJournalTable: RoomDatabase() {
    abstract fun expenseJournalDao(): ExpenseJournalDao
}