package com.example.homefinancialassistant.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SettingFragmentViewModel: ViewModel() {

    private val _nameProfile: MutableStateFlow<String> = MutableStateFlow("Гость")
    val nameProfile = _nameProfile

    private val _darkTheme: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val darkTheme = _darkTheme

    private val _startScreen: MutableStateFlow<String> = MutableStateFlow("Главный экран")
    val starScreen = _startScreen

    fun changeTheme() {
        _darkTheme.value = !_darkTheme.value
    }
}