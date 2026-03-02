package org.connect.chat.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.connect.chat.platform.LocationData
import org.connect.chat.platform.LocationManager

class LocationRepository(
    private val locationManager: LocationManager
) {
    suspend fun getCurrentLocation(): LocationData {
        return locationManager.getLocation()
    }

    fun startLocationUpdates(
        intervalSeconds: Int,
        onLocationUpdate: (LocationData) -> Unit
    ){
        locationManager.listenLocation(intervalSeconds) { location ->
            onLocationUpdate(location)
        }
    }

    fun stopLocationListen()  {
        locationManager.stopLocationListen()
    }
}