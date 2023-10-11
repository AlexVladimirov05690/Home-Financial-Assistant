package com.example.homefinancialassistant.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.data.CategoryConsumption
import com.example.homefinancialassistant.domain.Interactor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeScreenViewModel: ViewModel() {
    @Inject
    lateinit var interactor: Interactor
    //private val categoryConsumptionListFromDb: List<CategoryConsumption>
    val categoryConsumptionList: MutableStateFlow<List<CategoryConsumption>> = MutableStateFlow(
        emptyList()
    )

    init {
        App.instance.dagger.inject(this)
       // categoryConsumptionListFromDb = emptyList()
        updateList()
    }

    fun updateList(){
        viewModelScope.launch {
            categoryConsumptionList.value = list
        }
    }

    fun changeTest() {
            categoryConsumptionList.value = list2

    }

    companion object {
        val list = listOf(CategoryConsumption("Автомобиль", "123", 950.0, 30.0))
        val list2 = listOf(CategoryConsumption("Квартплата", "123", 950.0, 80.0))
    }
}