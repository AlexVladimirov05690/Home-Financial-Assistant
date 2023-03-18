package com.example.homefinancialassistant.utils

import com.example.homefinancialassistant.data.Enity.CurrencyFreaksApiDTO
import com.example.homefinancialassistant.data.RateCurrency

object ConverterDTO {
    fun converterDTOToRate(currencyFreaksApiDTO: CurrencyFreaksApiDTO): RateCurrency {
        val name = "EUR"
        return RateCurrency(
            name,
            currencyFreaksApiDTO.date,
            currencyFreaksApiDTO.rates[name] ?: 8888.8888
        )
    }
}