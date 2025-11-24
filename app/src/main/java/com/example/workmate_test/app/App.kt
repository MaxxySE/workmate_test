package com.example.workmate_test.app

import android.app.Application
import com.example.workmate_test.di.appModule
import com.example.workmate_test.di.databaseModule
import com.example.workmate_test.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(appModule, networkModule, databaseModule))
        }

    }

}