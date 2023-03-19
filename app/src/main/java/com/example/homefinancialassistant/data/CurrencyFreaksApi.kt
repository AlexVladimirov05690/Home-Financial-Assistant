package com.example.homefinancialassistant.data

import com.example.homefinancialassistant.data.Enity.CurrencyFreaksApiDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyFreaksApi {
    @GET("latest/")
    suspend fun getRate(
        @Query("apikey") apikey: String,
        @Query("symbols") symbols: String
    ): CurrencyFreaksApiDTO
}