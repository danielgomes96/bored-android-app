package com.devlabs.boredapp

import android.app.Application
import com.devlabs.activities.di.viewModelModule
import com.devlabs.data.di.networkModule
import com.devlabs.data.di.repositoryModule
import com.devlabs.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BoredApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BoredApplication)
            modules(listOf(domainModule, viewModelModule, networkModule, repositoryModule))
        }
    }
}