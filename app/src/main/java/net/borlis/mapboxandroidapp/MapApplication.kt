package net.borlis.mapboxandroidapp

import android.app.Application
import net.borlis.mapboxandroidapp.di.appModule
import net.borlis.mapboxandroidapp.di.repositoryModule
import net.borlis.mapboxandroidapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class MapApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val koinModules: List<Module> = listOf(
            appModule,
//            fragmentModule,
            viewModelModule,
            repositoryModule,
//            dataSourceModule,
//            utilsModule
        )

        startKoin {
            androidLogger()
            androidContext(this@MapApplication)
            modules(koinModules)
        }
    }
}