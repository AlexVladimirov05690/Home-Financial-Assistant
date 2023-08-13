package com.example.homefinancialassistant.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.homefinancialassistant.data.CategoryConsumption
import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.data.Income
import com.example.homefinancialassistant.data.RateCurrencyEntity
import com.example.homefinancialassistant.data.dao.CacheAllSpendingDao
import com.example.homefinancialassistant.data.dao.ExpenseJournalDao
import com.example.homefinancialassistant.data.dao.IncomeDao
import com.example.homefinancialassistant.data.dao.RateCurrencyDao
import com.example.homefinancialassistant.data.db.converters.Converters

@Database(entities = [Consumption::class, CategoryConsumption::class, RateCurrencyEntity::class, Income::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDataBase: RoomDatabase() {
    abstract fun expenseJournalDao(): ExpenseJournalDao
    abstract fun cacheAllSpendingDao(): CacheAllSpendingDao
    abstract fun rateCurrencyDao(): RateCurrencyDao
    abstract fun incomeDao(): IncomeDao
}