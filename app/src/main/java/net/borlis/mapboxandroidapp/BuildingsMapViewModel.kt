package net.borlis.mapboxandroidapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.borlis.mapboxandroidapp.data.models.BuildingModel
import net.borlis.mapboxandroidapp.domain.BuildingsRepository
import net.borlis.mapboxandroidapp.domain.UnexpectedErrorRepository
import net.borlis.mapboxandroidapp.extensions.launchIO
import net.borlis.mapboxandroidapp.domain.Result

class BuildingsMapViewModel(
    private val buildingsRepository: BuildingsRepository,
    private val unexpectedErrorRepository: UnexpectedErrorRepository
) : ViewModel() {
    private val _inProgress: MutableLiveData<Boolean> = MutableLiveData(false)
    val inProgress: LiveData<Boolean> = _inProgress
    private val _buildingsPointers: MutableLiveData<List<BuildingModel>> =
        MutableLiveData<List<BuildingModel>>()
    val buildingsPointers: LiveData<List<BuildingModel>> = _buildingsPointers

    fun getBuildings() {
        _inProgress.value = true

        launchIO {
            val result = buildingsRepository.getBuildings()
            _inProgress.postValue(false)
            when (result) {
                is Result.Success -> _buildingsPointers.postValue(result.data)
                is Result.Error -> unexpectedErrorRepository.registerUnexpectedError(result.exception)
            }
        }
    }
}