package com.example.homefinancialassistant.utils

import com.example.homefinancialassistant.data.RateCurrency
import com.example.homefinancialassistant.data.enity.CurrencyFreaksApiDTO

object ConverterDTO {
    fun converterDTOToRate(currencyDTOList: CurrencyFreaksApiDTO): List<RateCurrency> {
        val result = mutableListOf<RateCurrency>()
        result.add(0, RateCurrency("EUR", currencyDTOList.date, currencyDTOList.rates["EUR"]!!))
        result.add(1, RateCurrency("RUB", currencyDTOList.date, currencyDTOList.rates["RUB"]!!))
        result.add(2, RateCurrency("BTC", currencyDTOList.date, currencyDTOList.rates["BTC"]!!))
        return result
    }
}