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

    val courseDollar: MutableLiveData<Double> by lazy {
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
            println("Запрос данных")
            val resultDb = async {
                interactor.ratesFromDb().forEach {
                    mapFromDb[it.currency] = it.rate
                }
            }
            resultDb.await()
            println("Начало отображения данных")
            courseDollar.postValue(fromMap(mapFromDb, "RUB"))
            courseEuro.postValue(fromMap(mapFromDb, "EUR"))
            courseBtc.postValue(fromMap(mapFromDb, "BTC"))
        }
    }

    private fun fromMap(map: Map<String, Double>, key: String) : Double {
        return if(key == "RUB") {
            mathHelper.rounding(map[key] ?: 9999.0)
        } else {
            mathHelper.rounding((map["RUB"] ?: 9999.0) / (map[key] ?: 9999.0))
        }
    }

}
