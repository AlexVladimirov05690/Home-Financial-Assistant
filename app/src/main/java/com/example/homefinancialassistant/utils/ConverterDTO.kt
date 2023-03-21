package com.example.homefinancialassistant.utils

import com.example.homefinancialassistant.data.Enity.CurrencyDTO
import com.example.homefinancialassistant.data.Enity.CurrencyFreaksApiDTO
import com.example.homefinancialassistant.data.RateCurrency

object ConverterDTO {
    fun converterDTOToRate(currencyDTOList: CurrencyFreaksApiDTO): Map<String, Double> {
        return currencyDTOList.rates
    }
}