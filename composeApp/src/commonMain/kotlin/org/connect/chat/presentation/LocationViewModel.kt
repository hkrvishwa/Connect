package org.connect.chat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.connect.chat.data.LocationRepository
import org.connect.chat.platform.LocationData

class LocationViewModel(
    private val repository: LocationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LocationState())
    val state: StateFlow<LocationState> = _state.asStateFlow()

    fun getCurrentLocation() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            try {
                val location = repository.getCurrentLocation()
                _state.value = _state.value.copy(
                    isLoading = false,
                    currentLocation = location,
                    error = null
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }

    fun startTracking(intervalSeconds: Int) {
        viewModelScope.launch {
            repository.startLocationUpdates(intervalSeconds) { location ->
                _state.value = _state.value.copy(
                    currentLocation = location,
                    isTracking = true
                )
            }
        }
    }

    fun stopTracking() {
        viewModelScope.launch {
            repository.stopLocationListen()
            _state.value = _state.value.copy(isTracking = false)
        }
    }
}


data class LocationState(
    val isLoading: Boolean = false,
    val currentLocation: LocationData? = null,
    val error: String? = null,
    val isTracking: Boolean = false
)