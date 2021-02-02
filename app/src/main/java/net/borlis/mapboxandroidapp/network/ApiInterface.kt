package net.borlis.mapboxandroidapp.network

import net.borlis.mapboxandroidapp.network.data.models.BuildingModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("uc?export=view&id=1Q74IPl1PyhbPu2RWXMWmauZ6g_QtUzO1")
    suspend fun getBuildings(): Response<List<BuildingModel>>
}