package com.example.homefinancialassistant.utils

import com.example.homefinancialassistant.App
import javax.inject.Inject
import kotlin.math.pow

class Credit {

    @Inject
    lateinit var mathHelper: MathHelper

    init {
        App.instance.dagger.inject(this)
    }

    fun monthlyPayment(amount: Double, tern: Double, percent: Double): Double {
        return mathHelper.rounding(amount * getAnnuityRatio(tern, percent))
    }

    fun overpayment(amount: Double, tern: Double, percent: Double): Double {
        return mathHelper.rounding(monthlyPayment(amount, tern, percent) * tern - amount)
    }

    private fun getAnnuityRatio(tern: Double, percent: Double): Double {
        val monthlyInterestRate = percent / 12 / 100
        val temp = (1 + monthlyInterestRate).pow(tern)
        return (monthlyInterestRate * temp) / (temp - 1)
    }

    fun getPercent(amount: Double, tern: Double, monthlyPayment: Double): Double {
        val annuityRatio = monthlyPayment / amount
        println(annuityRatio)
        var iteration = 0.0
        do {
            iteration += 0.001
            val difference = annuityRatio - getAnnuityRatio(tern, iteration)
            println(difference)
        } while (difference > 0)

        return mathHelper.rounding(iteration)
    }
}



