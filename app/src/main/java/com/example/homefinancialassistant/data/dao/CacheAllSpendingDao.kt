package com.example.homefinancialassistant.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homefinancialassistant.data.CategoryConsumption
import kotlinx.coroutines.flow.Flow

@Dao
interface CacheAllSpendingDao {

    @Query("SELECT * FROM spending_by_category")
    fun getAllFromSpending(): Flow<List<CategoryConsumption>>

    @Query("SELECT category FROM spending_by_category")
    suspend fun getCategoryFromSpending(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCategoryConsumption(categoryConsumption: CategoryConsumption)

}