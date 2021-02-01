package net.borlis.mapboxandroidapp

import android.app.Application
import com.facebook.stetho.Stetho
import net.borlis.mapboxandroidapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class MapApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val koinModules: List<Module> = listOf(
            appModule,
            viewModelModule,
            repositoryModule,
            dataSourceModule,
            utilsModule
        )

        startKoin {
            androidLogger()
            androidContext(this@MapApplication)
            modules(koinModules)
        }

        Stetho.initializeWithDefaults(this)
    }
}