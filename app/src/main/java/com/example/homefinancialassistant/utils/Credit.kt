package com.example.homefinancialassistant.utils

import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.utils.math.AnnuityRatio
import javax.inject.Inject

class Credit() {
    @Inject
    lateinit var annuityRatio: AnnuityRatio

    init {
        App.instance.dagger.inject(this)
    }

    fun monthlyPayment(amount: Double, tern: Double, percent: Double): Double {
        return amount * annuityRatio.getAnnuityRatio(tern, percent)
    }

    fun overpayment(amount: Double, tern: Double, percent: Double): Double {
        return (monthlyPayment(amount, tern, percent) * tern - amount)
    }


}