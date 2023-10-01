package com.example.homefinancialassistant.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.data.CategoryConsumption
import com.example.homefinancialassistant.domain.Interactor
import com.example.homefinancialassistant.utils.MathHelper
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import java.util.Random
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    private val totalPrice: Flow<Double>
    private val totalIncome: Flow<Double>

    @Inject
    lateinit var interactor: Interactor

    @Inject
    lateinit var mathHelper: MathHelper


    init {
        App.instance.dagger.inject(this)
        totalPrice = interactor.getAllPriceFromDb()
        totalIncome = interactor.getAllIncomeFromDb()
    }

    private suspend fun getUniqueCategoryFromDb(): List<String> {
        val result = viewModelScope.async {
            val resultList = mutableListOf<String>()
            resultList.addAll(interactor.getCategoriesFromDb())
            resultList.sort()
            resultList.distinct()
            deleteDuplicate(resultList)
        }

        return result.await()
    }

    private fun deleteDuplicate(list: List<String>): MutableList<String> {
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
        val priceAll = totalPrice.firstOrNull() ?: 0.0
        val result = viewModelScope.async {
            listCategory.forEach { category ->
                mapResult[category] = percentToAngle(toPercent(categoryPrice(category), priceAll))
            }
            mapResult
        }
        return result.await()
    }

    suspend fun categoryPrice(category: String): Double {
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
        return mathHelper.rounding((price / totalPrice * 100).toFloat())
    }

    private fun percentToAngle(percent: Float): Float {
        return percent / 100 * 360
    }

    suspend fun getBalance(): Double {
        val balanceScope = viewModelScope.async {
            val allIncome: Double = totalIncome.firstOrNull() ?: 0.0
            val allConsumption = totalPrice.firstOrNull() ?: 0.0
            val balance = mathHelper.rounding(allIncome - allConsumption)
            balance
        }
        return balanceScope.await()
    }

    suspend fun getTotalConsumptionPrice(): Double {
        val resultFromDb = viewModelScope.async {
            val allPrice: Double = mathHelper.rounding(totalPrice.firstOrNull() ?: 0.0)
            allPrice
        }
        return resultFromDb.await()
    }

    suspend fun updateSpendingByCategory() {
        val listUniqueCategory = interactor.getUniqueCategoriesFromDb()
        val totalPrice = totalPrice.firstOrNull() ?: 0.0
        listUniqueCategory.forEach {
            interactor.addToSpending(CategoryConsumption(it, randomColor(), categoryPrice(it), toPercent(categoryPrice(it), totalPrice).toDouble()))
        }
    }

    private fun randomColor(): Int {
        val rnd = Random()
        return rnd.nextInt()
    }
}






