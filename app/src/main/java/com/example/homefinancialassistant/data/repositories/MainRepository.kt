package com.example.homefinancialassistant.data.repositories

import com.example.homefinancialassistant.data.CategoryConsumption
import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.data.RateCurrencyEntity
import com.example.homefinancialassistant.data.dao.CacheAllSpendingDao
import com.example.homefinancialassistant.data.dao.ExpenseJournalDao
import com.example.homefinancialassistant.data.dao.RateCurrencyDao
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class MainRepository(
    private val rateCurrencyDao: RateCurrencyDao,
    private val expenseJournalDao: ExpenseJournalDao,
    private val cacheAllSpendingDao: CacheAllSpendingDao
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

    suspend fun getCategoriesFromSpending(): List<String> {
        return cacheAllSpendingDao.getCategoryFromSpending()
    }

    fun getAllFromSpending(): Flow<List<CategoryConsumption>> {
        return cacheAllSpendingDao.getAllFromSpending()
    }

    fun insertToSpending(categoryConsumption: CategoryConsumption) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        val scope = CoroutineScope(Job())
        scope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            cacheAllSpendingDao.addCategoryConsumption(categoryConsumption)
        }
    }
}