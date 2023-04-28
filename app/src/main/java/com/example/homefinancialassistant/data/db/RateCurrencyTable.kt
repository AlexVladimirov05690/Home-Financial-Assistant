package com.example.homefinancialassistant.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homefinancialassistant.data.RateCurrencyEntity
import com.example.homefinancialassistant.data.dao.RateCurrencyDao

@Database(entities = [RateCurrencyEntity::class], version = 1, exportSchema = false)
abstract class RateCurrencyTable: RoomDatabase() {
    abstract fun rateCurrencyDao(): RateCurrencyDao
}