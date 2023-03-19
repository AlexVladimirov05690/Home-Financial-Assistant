package com.example.homefinancialassistant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.domain.Interactor
import com.example.homefinancialassistant.domain.InteractorNetwork
import javax.inject.Inject

class ExchangeRatesFragmentViewModel: ViewModel() {


    @Inject
    lateinit var interactorNetwork: InteractorNetwork

    init {
        App.instance.dagger.inject(this)
    }

    val courseDollar: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    fun updateCourseDollar() {
        courseDollar.value = interactorNetwork.getRateEuroToDollar()
    }
}