package net.borlis.mapboxandroidapp.domain

import net.borlis.mapboxandroidapp.data.BuildingsDataSource
import net.borlis.mapboxandroidapp.network.data.models.BuildingModel

class BuildingsRepository(private val dataSource: BuildingsDataSource) {

    suspend fun getBuildings(): Result<List<BuildingModel>> {
        return dataSource.getBuildings()
    }
}