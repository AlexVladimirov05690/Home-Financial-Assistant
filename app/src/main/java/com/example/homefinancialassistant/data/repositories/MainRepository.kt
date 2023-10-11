package com.example.homefinancialassistant.data.repositories

import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.data.CategoryConsumption
import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.data.Income
import com.example.homefinancialassistant.data.RateCurrencyEntity
import com.example.homefinancialassistant.data.dao.CacheAllSpendingDao
import com.example.homefinancialassistant.data.dao.ExpenseJournalDao
import com.example.homefinancialassistant.data.dao.IncomeDao
import com.example.homefinancialassistant.data.dao.RateCurrencyDao
import com.example.homefinancialassistant.utils.MathHelper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import java.util.Random
import javax.inject.Inject

class MainRepository(
    private val rateCurrencyDao: RateCurrencyDao,
    private val expenseJournalDao: ExpenseJournalDao,
    private val cacheAllSpendingDao: CacheAllSpendingDao,
    private val incomeDao: IncomeDao
) {

    @Inject
    lateinit var mathHelper: MathHelper

    init {
        App.instance.dagger.inject(this)
    }

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

    suspend fun insertConsumption(consumption: Consumption) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        val scope = CoroutineScope(Job() + coroutineExceptionHandler)
        val load = scope.launch(Dispatchers.IO) {
            expenseJournalDao.insert(consumption)
            updateSpendingDb()
        }
    }

    fun insertIncome(income: Income) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        val scope = CoroutineScope(Job())
        scope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            incomeDao.insertIncome(income)
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
            updateSpendingDb()
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

    fun getAllIncome(): Flow<Double> {
        return incomeDao.getAllIncome()
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

    private suspend fun updateSpendingDb() {
        val totalPrice = expenseJournalDao.getAllPrice().firstOrNull() ?: 0.0
        val listCategoryConsumption = expenseJournalDao.getListCategory()
        val scope = CoroutineScope(Job())
        scope.launch {
            cacheAllSpendingDao.deleteAll()
            listCategoryConsumption.forEach {
                val priceConsumption = expenseJournalDao.getPriceConsumption(it).firstOrNull() ?: 0.0
                cacheAllSpendingDao.addCategoryConsumption(CategoryConsumption(it, "", priceConsumption,mathHelper.toPercent(priceConsumption, totalPrice).toDouble()))
            }
        }

    }

    private fun randomColor(): Int {
        val rnd = Random()
        return rnd.nextInt()
    }
}