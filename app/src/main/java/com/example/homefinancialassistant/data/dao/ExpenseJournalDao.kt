package com.example.homefinancialassistant.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homefinancialassistant.data.Consumption

@Dao
interface ExpenseJournalDao {
    @Query("SELECT * FROM expense_journal")
    suspend fun getAll(): List<Consumption>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(consumption: Consumption)

//    @Query("SELECT * FROM expense_journal WHERE id =:simpleId")
//    fun getSimple(simpleId : Int): Consumption
}