package com.gaurav.newsapp

import android.app.Application
import com.gaurav.newsapp.core.di.networkModules
import com.gaurav.newsapp.core.di.repoModules
import com.gaurav.newsapp.core.di.vmModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App :Application(){
    override fun onCreate() {
        super.onCreate()
        configKoin()
        configTimber()
    }

    private fun configKoin(){
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModules,
                    vmModules,
                    repoModules
                )
            )
        }
    }

    private fun configTimber() {
        Timber.plant(Timber.DebugTree())
    }



}