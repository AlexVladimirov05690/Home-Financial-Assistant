package com.example.homefinancialassistant.di

import com.example.homefinancialassistant.di.modules.MathModule
import com.example.homefinancialassistant.domain.Interactor
import com.example.homefinancialassistant.utils.Credit
import com.example.homefinancialassistant.viewmodels.CreditCalculatorFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MathModule::class])
interface AppComponent {
    fun inject(credit: Credit)
    fun inject(interactor: Interactor)
    fun inject(creditCalculatorFragmentViewModel: CreditCalculatorFragmentViewModel)
}