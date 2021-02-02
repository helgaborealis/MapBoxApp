package net.borlis.mapboxandroidapp.network.data.models

data class BuildingModel(
    val id: Int,
    val title: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val mainImage: String
)
