package com.example.homefinancialassistant.di.modules

import com.example.homefinancialassistant.domain.Interactor
import com.example.homefinancialassistant.utils.Credit
import dagger.Module
import dagger.Provides

@Module
class MathModule {

    @Provides
    fun provideCredit() = Credit()

    @Provides
    fun provideInteractor() = Interactor()
}