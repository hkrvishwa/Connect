package org.connect.chat

import android.app.Application
import org.connect.chat.di.androidModule
import org.connect.chat.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(commonModule,androidModule)
        }
    }
}