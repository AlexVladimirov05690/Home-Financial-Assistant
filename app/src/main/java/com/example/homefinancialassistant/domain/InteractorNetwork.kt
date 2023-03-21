package com.example.homefinancialassistant.domain

import com.example.homefinancialassistant.API
import com.example.homefinancialassistant.data.CurrencyFreaksApi

class InteractorNetwork(val retrofitService: CurrencyFreaksApi) {

    suspend fun ratesToDollar(): Map<String, Double> {
        val listRate = mutableListOf<String>()
        listRate.add(0, "EUR")
        listRate.add(1, "RUB")
        listRate.add(2, "BTC")
        return (retrofitService.getRate(API.KEY, listRate)).rates
    }


}