package com.example.homefinancialassistant.di.modules

import android.content.Context
import com.example.homefinancialassistant.data.CurrencyFreaksApi
import com.example.homefinancialassistant.data.SettingProvider
import com.example.homefinancialassistant.data.repositories.MainRepository
import com.example.homefinancialassistant.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule(val context: Context) {

    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideSettingProvider(context: Context) = SettingProvider(context)

    @Provides
    @Singleton
    fun provideInteractor(
        currencyFreaksApi: CurrencyFreaksApi,
        mainRepository: MainRepository,
        settingProvider: SettingProvider
    ) = Interactor(retrofitService = currencyFreaksApi, mainRepository = mainRepository, settingProvider = settingProvider)
}