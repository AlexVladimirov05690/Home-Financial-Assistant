package com.example.homefinancialassistant.data.Enity

data class CurrencyDTO(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)