package com.example.homefinancialassistant.viewmodels

import androidx.lifecycle.ViewModel
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.domain.Interactor
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class ChangeStartScreenViewModel: ViewModel() {
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
    }

    private val _listOfScreens: MutableStateFlow<List<String>> = MutableStateFlow(listOf("Главный экран", "Кредитный калькулятор", "Конвверётр валют", "Журнал расходов"))
    val listOfScreen = _listOfScreens

    private val _selectedScreen: MutableStateFlow<String> = MutableStateFlow("Главный экран")
    val selectedScreen = _selectedScreen

    fun changedSelectedScreen(screen: String) {
        _selectedScreen.value = screen
    }

    fun changedDefaultScreen() {
        interactor.changeDefaultScreen(_selectedScreen.value)
    }
}