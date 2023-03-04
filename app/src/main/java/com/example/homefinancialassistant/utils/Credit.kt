package com.example.homefinancialassistant.utils

import kotlin.math.pow

class Credit() {

    fun monthlyPayment(amount: Double, tern: Double, percent: Double): Double {
        return amount * getAnnuityRatio(tern, percent)
    }

    fun overpayment(amount: Double, tern: Double, percent: Double): Double {
        return (monthlyPayment(amount, tern, percent) * tern - amount)
    }

    private fun getAnnuityRatio(tern: Double, percent: Double): Double {
        val monthlyInterestRate = percent / 12 / 100
        val temp = (1 + monthlyInterestRate).pow(tern)
        return (monthlyInterestRate * temp)/(temp - 1)
    }




}