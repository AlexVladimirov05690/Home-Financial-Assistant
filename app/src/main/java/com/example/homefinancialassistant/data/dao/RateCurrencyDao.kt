package com.example.homefinancialassistant.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homefinancialassistant.data.RateCurrency

@Dao
interface RateCurrencyDao {
    @Query("SELECT * FROM rates_table")
    suspend fun getAll(): List<RateCurrency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listRates: List<RateCurrency>)


}