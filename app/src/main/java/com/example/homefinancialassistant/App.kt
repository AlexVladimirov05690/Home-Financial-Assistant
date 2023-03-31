package com.example.homefinancialassistant

import android.app.Application
import com.example.homefinancialassistant.di.AppComponent
import com.example.homefinancialassistant.di.DaggerAppComponent
import com.example.homefinancialassistant.di.modules.DomainModule

class App: Application() {
    lateinit var dagger: AppComponent

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        dagger = DaggerAppComponent.builder()
            .domainModule(DomainModule(this))
            .build()
    }
}

