package com.example.homefinancialassistant.domain

import com.example.homefinancialassistant.API
import com.example.homefinancialassistant.data.CurrencyFreaksApi
import com.example.homefinancialassistant.data.Enity.CurrencyFreaksApiDTO
import com.example.homefinancialassistant.data.RateCurrency
import com.example.homefinancialassistant.utils.ConverterDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InteractorNetwork(val retrofitService: CurrencyFreaksApi) {
    fun getRateEuroToDollar(): RateCurrency {
        var result = RateCurrency("EUR", "000000", 9999.9999)
        retrofitService.getRate(API.KEY, "EUR")
            .enqueue(object : Callback<CurrencyFreaksApiDTO> {
                override fun onResponse(
                    call: Call<CurrencyFreaksApiDTO>,
                    response: Response<CurrencyFreaksApiDTO>
                ) {
                    result = response.body()?.let { ConverterDTO.converterDTOToRate(it) }!!
                }

                override fun onFailure(call: Call<CurrencyFreaksApiDTO>, t: Throwable) {
                    println(t)
                    println("Ошибка")
                }

            })
        return result
    }
}