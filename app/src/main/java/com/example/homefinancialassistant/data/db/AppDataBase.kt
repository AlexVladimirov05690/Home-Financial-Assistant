package com.example.homefinancialassistant.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import com.example.homefinancialassistant.data.CategoryConsumption
import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.data.Income
import com.example.homefinancialassistant.data.RateCurrencyEntity
import com.example.homefinancialassistant.data.dao.CacheAllSpendingDao
import com.example.homefinancialassistant.data.dao.ExpenseJournalDao
import com.example.homefinancialassistant.data.dao.IncomeDao
import com.example.homefinancialassistant.data.dao.RateCurrencyDao
import com.example.homefinancialassistant.data.db.converters.Converters

@Database(entities = [Consumption::class, CategoryConsumption::class, RateCurrencyEntity::class, Income::class], version = 2, autoMigrations = [AutoMigration(from = 1, to = 2, spec = AppDataBase.MyAutoMigration::class)], exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDataBase: RoomDatabase() {
    @DeleteColumn("spending_by_category", "color")
    class MyAutoMigration: AutoMigrationSpec
    abstract fun expenseJournalDao(): ExpenseJournalDao
    abstract fun cacheAllSpendingDao(): CacheAllSpendingDao
    abstract fun rateCurrencyDao(): RateCurrencyDao
    abstract fun incomeDao(): IncomeDao
}
