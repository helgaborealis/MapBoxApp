package net.borlis.mapboxandroidapp

import androidx.lifecycle.ViewModel
import net.borlis.mapboxandroidapp.data.BuildingsRepository

class BuildingsMapViewModel(
    private val repo: BuildingsRepository
) : ViewModel() {

    fun getBuildings() {
        repo.getBuildings()
    }
}