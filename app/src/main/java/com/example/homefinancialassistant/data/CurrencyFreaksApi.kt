package com.example.homefinancialassistant.data

import com.example.homefinancialassistant.data.enity.CurrencyFreaksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyFreaksApi {
    @GET("latest/")
    suspend fun getRate(
        @Query("apikey") apikey: String,
        @Query("symbols") symbols: List<String>
    ): CurrencyFreaksResponse
}