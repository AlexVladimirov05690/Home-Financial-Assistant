package com.example.homefinancialassistant.data.repositories

import com.example.homefinancialassistant.data.RateCurrency
import com.example.homefinancialassistant.data.dao.RateCurrencyDao
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class MainRepository(private val rateCurrencyDao: RateCurrencyDao) {

    fun getAllFromRateCurrencyDb(): Flow<List<RateCurrency>> {
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