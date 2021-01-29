package net.borlis.mapboxandroidapp.data

import net.borlis.mapboxandroidapp.data.models.BuildingModel
import net.borlis.mapboxandroidapp.domain.Result
import net.borlis.mapboxandroidapp.network.ApiClient

interface BuildingsRepository {
    suspend fun getBuildings(): Result<List<BuildingModel>>
}

class BuildingsRepositoryImpl(private val apiClient: ApiClient) :
    BuildingsRepository {
    override suspend fun getBuildings(): Result<List<BuildingModel>> {
        val result = apiClient.client.getBuildings()
        return Result<re>
    }
}