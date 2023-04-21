package com.example.homefinancialassistant.domain

import android.icu.util.Calendar
import com.example.homefinancialassistant.API
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.data.CurrencyFreaksApi
import com.example.homefinancialassistant.data.RateCurrency
import com.example.homefinancialassistant.data.SettingProvider
import com.example.homefinancialassistant.data.repositories.MainRepository
import com.example.homefinancialassistant.utils.ConverterDTO
import com.example.homefinancialassistant.utils.Credit
import kotlinx.coroutines.*
import javax.inject.Inject

class Interactor(
    val retrofitService: CurrencyFreaksApi,
    val mainRepository: MainRepository,
    val settingProvider: SettingProvider
) {
    @Inject
    lateinit var credit: Credit

    init {
        App.instance.dagger.inject(this)
    }

    fun getMonthlyPayment(amount: Double, tern: Double, percent: Double): Double {
        return credit.monthlyPayment(amount, tern, percent)
    }

    fun getOverpayment(amount: Double, tern: Double, percent: Double): Double {
        return credit.overpayment(amount, tern, percent)
    }

    fun getPercentBack(amount: Double, tern: Double, monthlyPayment: Double): Double {
        return credit.getPercent(amount, tern, monthlyPayment)
    }


    suspend fun ratesFromDb(): List<RateCurrency> {
        return if (settingProvider.dateCompare(Calendar.getInstance())) {
            mainRepository.getAllFromRateCurrencyDb()
        } else {
            val scope = CoroutineScope(Job())
            val result = scope.async {
                ratesFromApi()
            }
            result.await()
            mainRepository.getAllFromRateCurrencyDb()
        }
    }

    private suspend fun ratesFromApi() {
        val listRate = mutableListOf<String>()
        listRate.add(0, "EUR")
        listRate.add(1, "RUB")
        listRate.add(2, "BTC")
        val scope = CoroutineScope(Job())
        println("Запрос в сеть")
        scope.launch(Dispatchers.IO) {
            try {
                mainRepository.insertAllToRateCurrencyDb(
                    ConverterDTO.converterDTOToRate(
                        retrofitService.getRate(API.KEY, listRate)
                    )
                )
                println("Получен ответ от сервера и записаны данные в базу")
                settingProvider.changeDate(Calendar.getInstance())
            } catch (e: Exception) {
                println(e)
            }
        }
    }

}