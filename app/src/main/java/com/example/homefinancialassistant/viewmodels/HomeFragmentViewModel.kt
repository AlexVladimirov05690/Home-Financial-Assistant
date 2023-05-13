package com.example.homefinancialassistant.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.domain.Interactor
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    val totalPrice: Flow<Double>


    @Inject
    lateinit var interactor: Interactor


    init {
        App.instance.dagger.inject(this)
        totalPrice = interactor.getAllPriceFromDb()
    }

    private suspend fun getUniqueCategoryFromDb(): List<String> {
        val result = viewModelScope.async {
            val resultList = mutableListOf<String>()
            resultList.addAll(interactor.getCategoriesFromDb())
            resultList.sort()
            resultList.distinct()
            println("!!!$resultList")
            Log.e("!!!", "закончено формирование списка категорий")
            deleteDublicate(resultList)
        }

        return result.await()
    }

    private fun deleteDublicate(list: List<String>): MutableList<String> {
        val result = mutableListOf<String>()
        return if (list.isEmpty()) result
        else {
            result.add(0, list[0])
            var i = 0
            var y = 0
            list.forEach {
                if (it == result.last()) {
                    i++
                } else {
                    result.add(y, it)
                    i++
                    y++
                }
            }
            result
        }
    }

    suspend fun consumptionToMap(): Map<String, Float> {
        val listCategory = getUniqueCategoryFromDb()
        val mapResult = mutableMapOf<String, Float>()
        val priceAll = totalPrice.first()
        val result = viewModelScope.async {
            listCategory.forEach { category ->
                mapResult[category] = percentToAngle(toPercent(categoryPrice(category), priceAll))
            }
            mapResult
        }
        return result.await()
    }

    private suspend fun categoryPrice(category: String): Double {
        val resultList = viewModelScope.async {
            var totalPrice = 0.0
            interactor.getAllConsumptionCategoryFromDb(category).forEach {
                totalPrice += it.price
            }
            totalPrice
        }

        return resultList.await()
    }

    private fun toPercent(price: Double, totalPrice: Double): Float {
        return (price / totalPrice * 100).toFloat()
    }

    private fun percentToAngle(percent: Float): Float {
        return percent / 100 * 360
    }


}






