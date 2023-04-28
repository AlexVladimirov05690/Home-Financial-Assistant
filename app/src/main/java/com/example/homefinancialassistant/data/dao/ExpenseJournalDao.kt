package com.example.homefinancialassistant.data.dao

import androidx.room.*
import com.example.homefinancialassistant.data.Consumption
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseJournalDao {
    @Query("SELECT * FROM expense_journal")
    fun getAll(): Flow<List<Consumption>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(consumption: Consumption)

    @Delete
    fun delete(consumption: Consumption)



//    @Query("SELECT * FROM expense_journal WHERE id =:simpleId")
//    fun getSimple(simpleId : Int): Consumption
}