package net.borlis.mapboxandroidapp.domain

import com.google.gson.JsonObject
import com.mapbox.geojson.Feature
import com.mapbox.geojson.Point
import net.borlis.mapboxandroidapp.data.models.BuildingModel

fun mapToFeatures(list: List<BuildingModel>): List<Feature> {
    val result = mutableListOf<Feature>()
    list.forEach {
        val properties = JsonObject()
        properties.addProperty(ID, it.id)
        properties.addProperty(ADDRESS, it.address)
        properties.addProperty(TITLE, it.title)
        properties.addProperty(IMAGE_URL, it.mainImage)
        result.add(
            Feature.fromGeometry(
                Point.fromLngLat(it.longitude, it.latitude),
                properties
            ),
        )
    }
    return result
}

const val ID = "id"
const val ADDRESS = "address"
const val TITLE = "title"
const val IMAGE_URL = "image url"
