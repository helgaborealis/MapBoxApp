package net.borlis.mapboxandroidapp.domain

import com.google.gson.JsonObject
import com.mapbox.geojson.Feature
import com.mapbox.geojson.Point
import net.borlis.mapboxandroidapp.data.models.BuildingModel

fun mapToFeatures(list: List<BuildingModel>): List<Feature> {
    val result = mutableListOf<Feature>()
    list.forEach {
        val properties = JsonObject()
        properties.addProperty("id", it.id)
        properties.addProperty("address", it.address)
        properties.addProperty("title", it.title)
        result.add(
            Feature.fromGeometry(
                Point.fromLngLat(it.longitude, it.latitude),
                properties
            ),
        )
    }
    return result
}
