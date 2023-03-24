package com.example.homefinancialassistant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.domain.InteractorNetwork
import com.example.homefinancialassistant.utils.MathHelper
import kotlinx.coroutines.*
import javax.inject.Inject

class ExchangeRatesFragmentViewModel: ViewModel() {

    @Inject
    lateinit var interactorNetwork: InteractorNetwork

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

    fun updateCourseDollar() {

        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }
        val scope = CoroutineScope(Job())
        scope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            courseEuro.postValue(mathHelper.rounding(interactorNetwork.ratesToDollar().getValue("EUR")))
            courseRub.postValue(mathHelper.rounding(interactorNetwork.ratesToDollar().getValue("RUB")))
            courseBtc.postValue(mathHelper.rounding(interactorNetwork.ratesToDollar().getValue("BTC")))
        }

    }
}