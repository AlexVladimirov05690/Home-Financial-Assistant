package com.example.homefinancialassistant.data.repositories

import com.example.homefinancialassistant.data.RateCurrency
import com.example.homefinancialassistant.data.dao.RateCurrencyDao
import kotlinx.coroutines.*

class MainRepository(private val rateCurrencyDao: RateCurrencyDao) {

    suspend fun getAllFromRateCurrencyDb(): List<RateCurrency> {
        println("Запрос в базу данных")
        return rateCurrencyDao.getAll()
    }

    fun insertAllToRateCurrencyDb(list: List<RateCurrency>) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        val scope = CoroutineScope(Job())
        scope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            rateCurrencyDao.insertAll(list)
        }
    }
}