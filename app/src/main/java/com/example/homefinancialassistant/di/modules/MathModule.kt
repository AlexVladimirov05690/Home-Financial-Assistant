package com.example.homefinancialassistant.di.modules

import com.example.homefinancialassistant.domain.Interactor
import com.example.homefinancialassistant.utils.Credit
import com.example.homefinancialassistant.utils.math.AnnuityRatio
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MathModule {
    @Provides
    @Singleton
    fun provideAnnuityRatio() = AnnuityRatio()

    @Provides
    fun provideCredit() = Credit()

    @Provides
    fun provideInteractor() = Interactor()
}