package com.example.homefinancialassistant.di.modules

import android.content.Context
import androidx.room.Room
import com.example.homefinancialassistant.data.dao.ExpenseJournalDao
import com.example.homefinancialassistant.data.dao.RateCurrencyDao
import com.example.homefinancialassistant.data.db.ExpenseJournalTable
import com.example.homefinancialassistant.data.db.RateCurrencyTable
import com.example.homefinancialassistant.data.repositories.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {


    @Singleton
    @Provides
    fun provideFilmDao(context: Context) =
        Room.databaseBuilder(
            context,
            RateCurrencyTable::class.java,
            "rates_table"
        ).build().rateCurrencyDao()

    @Singleton
    @Provides
    fun provideExpenseJournalDao(context: Context) =
        Room.databaseBuilder(
            context,
            ExpenseJournalTable::class.java,
            "expense_journal"
        ).build().expenseJournalDao()


    @Singleton
    @Provides
    fun provideRepository(rateCurrencyDao: RateCurrencyDao, expenseJournalDao: ExpenseJournalDao) =
        MainRepository(rateCurrencyDao, expenseJournalDao)

}