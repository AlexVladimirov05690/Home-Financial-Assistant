package com.example.homefinancialassistant.utils

import com.example.homefinancialassistant.data.RateCurrencyEntity
import com.example.homefinancialassistant.data.enity.CurrencyFreaksResponse

object ConverterDTO {
    fun converterDTOToRate(currencyDTOList: CurrencyFreaksResponse): List<RateCurrencyEntity> {
        val result = mutableListOf<RateCurrencyEntity>()
        result.add(0,
            RateCurrencyEntity("EUR", currencyDTOList.date, currencyDTOList.rates["EUR"]?: 0.0)
        )
        result.add(1,
            RateCurrencyEntity("RUB", currencyDTOList.date, currencyDTOList.rates["RUB"]?: 0.0)
        )
        result.add(2,
            RateCurrencyEntity("BTC", currencyDTOList.date, currencyDTOList.rates["BTC"]?: 0.0)
        )
        return result
    }
}