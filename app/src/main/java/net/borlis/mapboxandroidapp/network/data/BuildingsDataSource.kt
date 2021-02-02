package net.borlis.mapboxandroidapp.data

import net.borlis.mapboxandroidapp.network.data.models.BuildingModel
import net.borlis.mapboxandroidapp.domain.Result

interface BuildingsDataSource {
    suspend fun getBuildings(): Result<List<BuildingModel>>
}

class BuildingsDataSourceImpl(
    private val requestExecutor: RetrofitRequestExecutor
) :
    BuildingsDataSource {
    override suspend fun getBuildings(): Result<List<BuildingModel>> {
        val result = requestExecutor.execute { api -> api.client.getBuildings() }
        return result.convertSuccess { response -> response }
    }
}