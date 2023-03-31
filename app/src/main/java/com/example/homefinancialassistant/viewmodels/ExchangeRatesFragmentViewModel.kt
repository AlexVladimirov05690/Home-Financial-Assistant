package com.example.homefinancialassistant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.domain.Interactor
import com.example.homefinancialassistant.utils.MathHelper
import kotlinx.coroutines.*
import javax.inject.Inject

class ExchangeRatesFragmentViewModel : ViewModel() {

    @Inject
    lateinit var interactor: Interactor

    @Inject
    lateinit var mathHelper: MathHelper

    init {
        App.instance.dagger.inject(this)
    }

    val courseEuro: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val courseRub: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val courseBtc: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }


    fun updateRateCurrencyFromDb() {
        val mapFromDb = mutableMapOf<String, Double>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }
        val scope = CoroutineScope(Job())
        scope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            interactor.ratesFromDb().collect {list->
                withContext(Dispatchers.Main) {
                    list.forEach {
                        mapFromDb[it.currency] = it.rate
                        println(it.currency + it.rate.toString())
                        println(mapFromDb[it.currency])
                    }
                    courseRub.postValue(mathHelper.rounding(mapFromDb["RUB"] ?: 9999.0))
                    courseEuro.postValue(mathHelper.rounding(mapFromDb["EUR"] ?: 9999.0))
                    courseBtc.postValue(mathHelper.rounding(mapFromDb["BTC"] ?: 9999.0))
                }
            }
        }
    }
}