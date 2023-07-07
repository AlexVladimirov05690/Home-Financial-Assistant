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

    private val _darkTheme: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val darkTheme = _darkTheme

    private val _startScreen: MutableStateFlow<String> = MutableStateFlow(interactor.getDefaultScreen())
    val starScreen = _startScreen

    fun changeTheme() {
        _darkTheme.value = !_darkTheme.value
    }
}