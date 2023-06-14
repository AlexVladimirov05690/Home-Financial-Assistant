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
//        consumption.category = consumption.category.replace("\\s".toRegex(), "")
//        if (consumption.category.first().isLowerCase()) {
//            consumption.category.replaceFirstChar {
//            }
//        }
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

    suspend fun getCategories(): List<String> {
        return expenseJournalDao.getCategory()
    }

    fun getListCategories(): List<String> {
        return expenseJournalDao.getListCategory()
    }

    suspend fun getAllConsumptionCategory(category: String): List<Consumption> {
        return expenseJournalDao.getAllConsumptionCategory(category)
    }

    fun getAllPrice(): Flow<Double> {
        return expenseJournalDao.getAllPrice()
    }

}