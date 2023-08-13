package com.example.homefinancialassistant.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homefinancialassistant.data.Income
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao {

    @Query("SELECT SUM(sum) FROM income_journal")
    fun getAllIncome(): Flow<Double>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIncome(income: Income)
}