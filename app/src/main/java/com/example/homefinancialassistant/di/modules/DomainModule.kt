package com.example.homefinancialassistant.di.modules

import com.example.homefinancialassistant.data.CurrencyFreaksApi
import com.example.homefinancialassistant.domain.Interactor
import com.example.homefinancialassistant.domain.InteractorNetwork
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Provides
    @Singleton
    fun provideInteractorNetwork(currencyFreaksApi: CurrencyFreaksApi) = InteractorNetwork(retrofitService = currencyFreaksApi)
}