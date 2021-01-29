package net.borlis.mapboxandroidapp.data.models

data class BuildingModel(
    val id: String,
    val title: String,
    val address: String,
    val latitude: Long,
    val longitude: Long,
    val mainImage: String
)
