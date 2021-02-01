package net.borlis.mapboxandroidapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mapbox.geojson.Feature
import net.borlis.mapboxandroidapp.data.models.BuildingModel
import net.borlis.mapboxandroidapp.domain.*
import net.borlis.mapboxandroidapp.extensions.launchIO

class BuildingsMapViewModel(
    private val buildingsRepository: BuildingsRepository,
    private val unexpectedErrorRepository: UnexpectedErrorRepository
) : ViewModel() {
    private val _inProgress: MutableLiveData<Boolean> = MutableLiveData(false)
    val inProgress: LiveData<Boolean> = _inProgress
    private var _buildingsPointers: MutableLiveData<List<Feature>> =
        MutableLiveData<List<Feature>>()
    val buildingsPointers: LiveData<List<Feature>> = _buildingsPointers
    private var _filterPointers: MutableLiveData<List<Feature>> =
        MutableLiveData<List<Feature>>()
    val filterPointers = _filterPointers

    fun getBuildings() {
        _inProgress.value = true

        launchIO {
            val result = buildingsRepository.getBuildings()
            _inProgress.postValue(false)
            when (result) {
                is Result.Success -> _buildingsPointers.postValue(mapToFeatures(result.data)) // FIXME: 01.02.2021  mapping should be moved down
                is Result.Error -> unexpectedErrorRepository.registerUnexpectedError(result.exception)
            }
        }
    }

    fun filterPointers(query: String) {
        if (query.isNotEmpty()) {
            _filterPointers.postValue(buildingsPointers.value?.filter {
                it.getStringProperty(TITLE).contains(query)
            })
        } else {
            _filterPointers.postValue(buildingsPointers.value)
        }

    }
}