package com.example.homefinancialassistant.di

import com.example.homefinancialassistant.di.modules.DatabaseModule
import com.example.homefinancialassistant.di.modules.DomainModule
import com.example.homefinancialassistant.di.modules.MathModule
import com.example.homefinancialassistant.di.modules.RemoteModule
import com.example.homefinancialassistant.domain.Interactor
import com.example.homefinancialassistant.utils.Credit
import com.example.homefinancialassistant.viewmodels.AddConsumptionFragmentViewModel
import com.example.homefinancialassistant.viewmodels.ConsumptionFragmentViewModel
import com.example.homefinancialassistant.viewmodels.CreditCalculatorFragmentViewModel
import com.example.homefinancialassistant.viewmodels.ExchangeRatesFragmentViewModel
import com.example.homefinancialassistant.viewmodels.ExpenseJournalFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MathModule::class, RemoteModule::class, DomainModule::class, DatabaseModule::class])
interface AppComponent {
    fun inject(interactor: Interactor)
    fun inject(creditCalculatorFragmentViewModel: CreditCalculatorFragmentViewModel)
    fun inject(exchangeRatesFragmentViewModel: ExchangeRatesFragmentViewModel)
    fun inject(credit: Credit)
    fun inject(addConsumptionFragmentViewModel: AddConsumptionFragmentViewModel)
    fun inject(expenseJournalFragmentViewModel: ExpenseJournalFragmentViewModel)
    fun inject(consumptionFragmentViewModel: ConsumptionFragmentViewModel)
}