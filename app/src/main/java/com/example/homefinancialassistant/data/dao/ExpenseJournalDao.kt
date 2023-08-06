package com.example.homefinancialassistant.data.dao

import androidx.room.*
import com.example.homefinancialassistant.data.Consumption
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseJournalDao {
    @Query("SELECT * FROM expense_journal")
    fun getAll(): Flow<List<Consumption>>

    @Query("SELECT SUM(price) FROM expense_journal")
    fun getAllPrice(): Flow<Double>

    @Query("SELECT * FROM expense_journal WHERE category LIKE :category")
    suspend fun getAllConsumptionCategory(category: String): List<Consumption>


    @Query("SELECT category FROM expense_journal")
    suspend fun getCategory(): List<String>

    @Query("SELECT DISTINCT category FROM expense_journal")
    fun getListCategory(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(consumption: Consumption)

    @Delete
    fun delete(consumption: Consumption)
}