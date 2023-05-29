package com.example.homefinancialassistant.viewmodels

import androidx.lifecycle.ViewModel
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.domain.Interactor
import javax.inject.Inject

class ConsumptionFragmentViewModel: ViewModel() {
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
    }

    fun deleteConsumption(consumption: Consumption) {
        interactor.deleteConsumptionFromDb(consumption)
    }
}