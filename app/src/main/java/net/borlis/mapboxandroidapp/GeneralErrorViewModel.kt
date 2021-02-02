package net.borlis.mapboxandroidapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import net.borlis.mapboxandroidapp.domain.UnexpectedErrorRepository
import net.borlis.mapboxandroidapp.network.data.SingleHandleEvent

class GeneralErrorViewModel(errorRepository: UnexpectedErrorRepository) : ViewModel() {
    val generalErrorEvents: LiveData<SingleHandleEvent<Unit>> = errorRepository.errors
        .onEach { Log.e(TAG, it.message, it) }
        .map { SingleHandleEvent(Unit) }
        .asLiveData()

    companion object {
        private val TAG: String = GeneralErrorViewModel::class.java.simpleName
    }
}