package com.example.homefinancialassistant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.domain.Interactor
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
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

    val date: MutableLiveData<Calendar> by lazy {
        MutableLiveData<Calendar>()
    }

    suspend fun getCategoriesForAdapter(): List<String> {
        return interactor.getCategoriesFromSpending()
    }

    fun calendarToString(): String {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale.ROOT)
        return sdf.format(date.value?.time ?: Calendar.getInstance().time)
    }

    fun setToday() {
        date.value = Calendar.getInstance()
    }


    fun addConsumption() {
        viewModelScope.launch {
            val consumption = Consumption(
                date = date.value ?: Calendar.getInstance(),
                category = category.value ?: "",
                price = price.value ?: 0.0,
                description = description.value ?: ""
            )
            interactor.mainRepository.insertConsumption(consumption)
        }
    }

    companion object {
        const val DATE_FORMAT = "dd.MM.yyyy"
    }

}