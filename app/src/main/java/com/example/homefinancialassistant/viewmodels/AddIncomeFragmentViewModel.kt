package com.example.homefinancialassistant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.data.Income
import com.example.homefinancialassistant.domain.Interactor
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class AddIncomeFragmentViewModel: ViewModel() {
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
    }

    val sum: MutableLiveData<Double> by lazy {
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

    fun calendarToString(): String {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale.ROOT)
        return sdf.format(date.value?.time ?: Calendar.getInstance().time)
    }

    fun setToday() {
        date.value = Calendar.getInstance()
    }

    fun addIncome() {
        val income = Income(
            date = date.value ?: Calendar.getInstance(),
            category = category.value ?: "",
            sum = sum.value ?: 0.0,
            description = description.value ?: ""
        )
        interactor.mainRepository.insertIncome(income)
    }

    companion object {
        const val DATE_FORMAT = "dd.MM.yyyy"
    }
}