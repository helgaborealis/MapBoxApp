package net.borlis.mapboxandroidapp.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


inline fun ViewModel.launchIO(
    crossinline task: suspend () -> Unit
) = viewModelScope.launch(Dispatchers.IO) { task() }