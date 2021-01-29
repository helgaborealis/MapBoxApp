package net.borlis.mapboxandroidapp.network

import net.borlis.mapboxandroidapp.data.models.BuildingModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("file/d/1Q74IPl1PyhbPu2RWXMWmauZ6g_QtUzO1/view")
    suspend fun getBuildings(): Response<List<BuildingModel>>
}