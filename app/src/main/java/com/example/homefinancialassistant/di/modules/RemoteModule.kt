package com.example.homefinancialassistant.di.modules

import com.example.homefinancialassistant.BuildConfig
import com.example.homefinancialassistant.data.ApiConstants
import com.example.homefinancialassistant.data.CurrencyFreaksApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .callTimeout(ApiConstants.TIME_DELAY_SECOND, TimeUnit.SECONDS)
        .readTimeout(ApiConstants.TIME_DELAY_SECOND, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BASIC
            }
        })
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideCurrencyFreaksApi(retrofit: Retrofit): CurrencyFreaksApi = retrofit.create(CurrencyFreaksApi::class.java)
}