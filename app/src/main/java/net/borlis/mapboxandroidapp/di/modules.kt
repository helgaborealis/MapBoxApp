package net.borlis.mapboxandroidapp.di

import android.app.Application
import net.borlis.mapboxandroidapp.BuildingsMapViewModel
import net.borlis.mapboxandroidapp.data.BuildingsRepository
import net.borlis.mapboxandroidapp.data.BuildingsRepositoryImpl
import net.borlis.mapboxandroidapp.network.ApiClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { get<Application>().resources }
}

val viewModelModule = module {
    viewModel { BuildingsMapViewModel(get()) }
}

val repositoryModule = module {

    single<BuildingsRepository> { BuildingsRepositoryImpl(get()) }

    single {
        ApiClient()
    }
}