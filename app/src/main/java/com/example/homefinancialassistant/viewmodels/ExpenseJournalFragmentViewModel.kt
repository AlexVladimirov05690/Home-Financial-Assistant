package com.example.homefinancialassistant.viewmodels

import androidx.lifecycle.ViewModel
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.domain.Interactor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExpenseJournalFragmentViewModel : ViewModel() {

    val listConsumption: Flow<List<Consumption>>

    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        listConsumption = interactor.consumptionFromDb()
    }


}