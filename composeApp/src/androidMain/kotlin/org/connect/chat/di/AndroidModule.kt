package org.connect.chat.di

import androidx.activity.ComponentActivity
import org.connect.chat.platform.AndroidLocationManager
import org.connect.chat.platform.AndroidPermissionManager
import org.connect.chat.platform.LocationManager
import org.connect.chat.platform.PermissionManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {

    single<PermissionManager> {
        AndroidPermissionManager()
    }

    single<LocationManager> {
        AndroidLocationManager(androidContext())
    }
}