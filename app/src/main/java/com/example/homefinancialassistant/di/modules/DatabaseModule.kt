package com.example.homefinancialassistant.di.modules

import android.content.Context
import androidx.room.Room
import com.example.homefinancialassistant.data.db.AppDataBase
import com.example.homefinancialassistant.data.repositories.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDataBase(context: Context) =
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "App_data_base"
        ).build()

    @Singleton
    @Provides
    fun provideMainRepository(appDataBase: AppDataBase) = MainRepository(
        appDataBase.rateCurrencyDao(),
        appDataBase.expenseJournalDao(),
        appDataBase.cacheAllSpendingDao()
    )
}