package net.borlis.mapboxandroidapp.di

import android.app.Application
import net.borlis.mapboxandroidapp.BuildingsMapViewModel
import net.borlis.mapboxandroidapp.DialogManager
import net.borlis.mapboxandroidapp.GeneralErrorViewModel
import net.borlis.mapboxandroidapp.data.BuildingsDataSource
import net.borlis.mapboxandroidapp.data.BuildingsDataSourceImpl
import net.borlis.mapboxandroidapp.data.RetrofitRequestExecutor
import net.borlis.mapboxandroidapp.data.RetrofitRequestExecutorImpl
import net.borlis.mapboxandroidapp.domain.BuildingsRepository
import net.borlis.mapboxandroidapp.domain.UnexpectedErrorRepository
import net.borlis.mapboxandroidapp.network.ApiClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { get<Application>().resources }
}

val viewModelModule = module {
    viewModel { BuildingsMapViewModel(get(), get()) }
    viewModel { GeneralErrorViewModel(get()) }
}

val repositoryModule = module {

    single { UnexpectedErrorRepository() }

    single { BuildingsRepository(get()) }

    single {
        ApiClient()
    }
}

val dataSourceModule = module {
    single<BuildingsDataSource> { BuildingsDataSourceImpl(get()) }

    single<RetrofitRequestExecutor> { RetrofitRequestExecutorImpl(get()) }
}

val utilsModule = module {
    single {
        DialogManager()
    }
}