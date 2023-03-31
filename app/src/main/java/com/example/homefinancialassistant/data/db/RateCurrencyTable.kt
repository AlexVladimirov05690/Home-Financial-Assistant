package com.example.homefinancialassistant.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homefinancialassistant.data.RateCurrency
import com.example.homefinancialassistant.data.dao.RateCurrencyDao

@Database(entities = [RateCurrency::class], version = 1, exportSchema = false)
abstract class RateCurrencyTable: RoomDatabase() {
    abstract fun rateCurrencyDao(): RateCurrencyDao
}