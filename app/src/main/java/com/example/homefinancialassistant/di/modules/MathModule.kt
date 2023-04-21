package com.example.homefinancialassistant.di.modules

import com.example.homefinancialassistant.utils.Credit
import com.example.homefinancialassistant.utils.MathHelper
import dagger.Module
import dagger.Provides

@Module
class MathModule {

    @Provides
    fun provideCredit() = Credit()

    @Provides
    fun provideMathHelper() = MathHelper()
}