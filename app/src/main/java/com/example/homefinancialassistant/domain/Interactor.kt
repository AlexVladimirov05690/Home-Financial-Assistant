package com.example.homefinancialassistant.domain

import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.utils.Credit
import javax.inject.Inject

class Interactor {
    @Inject
    lateinit var credit: Credit

    init {
        App.instance.dagger.inject(this)
    }

    fun getMonthlyPayment(amount: Double, tern: Double, percent: Double): Double {
        return credit.monthlyPayment(amount, tern, percent)
    }

    fun getOverpayment(amount: Double, tern: Double, percent: Double): Double {
        return credit.overpayment(amount, tern, percent)
    }

    fun getPercentBack(amount: Double, tern: Double, monthlyPayment: Double): Double {
        return credit.getPercent(amount, tern, monthlyPayment)
    }
}