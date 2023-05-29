package com.example.homefinancialassistant.data.repositories

import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.data.RateCurrencyEntity
import com.example.homefinancialassistant.data.dao.ExpenseJournalDao
import com.example.homefinancialassistant.data.dao.RateCurrencyDao
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class MainRepository(
    private val rateCurrencyDao: RateCurrencyDao,
    private val expenseJournalDao: ExpenseJournalDao
) {

    suspend fun getAllFromRateCurrencyDb(): List<RateCurrencyEntity> {
        return rateCurrencyDao.getAll()
    }

    fun insertAllToRateCurrencyDb(list: List<RateCurrencyEntity>) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        val scope = CoroutineScope(Job())
        scope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            rateCurrencyDao.insertAll(list)
        }
    }

    fun insertConsumption(consumption: Consumption) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        val scope = CoroutineScope(Job())
        scope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            expenseJournalDao.insert(consumption)
        }
    }

    fun getAllConsumption(): Flow<List<Consumption>> {
        return expenseJournalDao.getAll()
    }

    fun deleteConsumption(consumption: Consumption) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        val scope = CoroutineScope(Job())
        scope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            expenseJournalDao.delete(consumption)
        }
    }

}