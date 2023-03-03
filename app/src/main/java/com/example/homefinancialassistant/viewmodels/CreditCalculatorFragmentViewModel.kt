package com.example.homefinancialassistant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.domain.Interactor
import javax.inject.Inject


class CreditCalculatorFragmentViewModel: ViewModel() {

    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
    }

    val amount: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }
    val tern: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }
    val percent: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }
    val overpayment: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }
    val monthlyPayment: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    fun calculation() {
        monthlyPayment.value = interactor.getMonthlyPayment(amount.value?: 0.0, tern.value?: 0.0, percent.value?: 0.0)
        overpayment.value = interactor.getOverpayment(amount.value?: 0.0, tern.value?: 0.0, percent.value?: 0.0)
    }


}