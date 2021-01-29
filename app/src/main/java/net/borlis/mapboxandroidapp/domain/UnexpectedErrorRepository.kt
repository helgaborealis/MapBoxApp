package net.borlis.mapboxandroidapp.domain

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class UnexpectedErrorRepository {
    private val _errors = Channel<Throwable>()
    val errors: Flow<Throwable>
        get() = _errors.receiveAsFlow()

    fun registerUnexpectedError(error: Throwable) {
        _errors.offer(error)
    }
}