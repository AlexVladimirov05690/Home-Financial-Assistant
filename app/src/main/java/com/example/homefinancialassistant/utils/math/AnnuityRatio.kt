package com.example.homefinancialassistant.utils.math
import kotlin.math.pow

//класс, отвечающий за коэффициент аннуитета
class AnnuityRatio {
    fun getAnnuityRatio(creditTerm: Double, interestRate: Double): Double {
        val monthlyInterestRate = interestRate / 12 / 100
        val temp = (1 + monthlyInterestRate).pow(creditTerm)
        return (monthlyInterestRate * temp)/(temp - 1)
    }
}