package com.example.homefinancialassistant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.domain.Interactor
import java.util.*
import javax.inject.Inject

class AddConsumptionFragmentViewModel : ViewModel() {
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
    }

    val price: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val category: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val description: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    suspend fun getCategoriesForAdapter(): List<String> {
        return interactor.getCategoriesFromSpending()
    }

    fun addConsumption() {
        val consumption = Consumption(
            date = Calendar.getInstance(),
            category = category.value ?: "",
            price = price.value ?: 0.0,
            description = description.value ?: ""
        )
        interactor.mainRepository.insertConsumption(consumption)
    }


}