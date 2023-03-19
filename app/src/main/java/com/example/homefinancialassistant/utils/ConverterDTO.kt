package com.example.homefinancialassistant.utils

import com.example.homefinancialassistant.data.Enity.CurrencyDTO
import com.example.homefinancialassistant.data.Enity.CurrencyFreaksApiDTO
import com.example.homefinancialassistant.data.RateCurrency

object ConverterDTO {
    fun converterDTOToRate(currencyDTO: CurrencyFreaksApiDTO): RateCurrency {
        val name = "EUR"
        return RateCurrency(
            name,
            currencyDTO.date,
            currencyDTO.rates[name]?:0.0
        )
    }
}