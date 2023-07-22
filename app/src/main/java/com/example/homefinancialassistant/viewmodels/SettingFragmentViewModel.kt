package com.example.homefinancialassistant.viewmodels

import androidx.lifecycle.ViewModel
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.domain.Interactor
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class SettingFragmentViewModel: ViewModel() {

    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
    }

    private val _nameProfile: MutableStateFlow<String> = MutableStateFlow("Гость")
    val nameProfile = _nameProfile

    private val _startScreen: MutableStateFlow<String> = MutableStateFlow(interactor.getDefaultScreen())
    val starScreen = _startScreen


    fun changeSmallTextTheme() : String {
        return when(interactor.getThemeApp()) {
            "auto" -> "Автоматическое изменение темы"
            "dark" -> "Тёмная тема"
            "light" -> "Светлая тема"
            else -> {""}
        }
    }


    fun changeTheme() {
        interactor.changeKeyStartScreenFromScreenSettings(true)
        interactor.changeThemeApp()
    }

    fun getAutoDarkTheme(): Boolean {
        return interactor.getAutoDarkTheme()
    }

    fun changeTextTheme(): String {
        return when(interactor.getThemeApp()) {
            "auto" -> "Включить светлую тему"
            "light" -> "Включить тёмную тему"
            "dark" -> "Включить режим устройства"
            else -> ""
        }
    }

}