package com.example.homefinancialassistant.data.Enity

import com.google.gson.annotations.SerializedName

data class CurrencyFreaksApiDTO(
    @SerializedName("date")
    val date: String,
    @SerializedName("base")
    val base: String,
    @SerializedName("rates")
    val rates: Map<String, Double>
)