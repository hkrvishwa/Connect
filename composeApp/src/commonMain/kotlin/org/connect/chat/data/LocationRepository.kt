package org.connect.chat.data

import org.connect.chat.platform.LocationData
import org.connect.chat.platform.LocationManager

class LocationRepository(
    private val locationManager: LocationManager,
    private val log : LogRepo
) {
    suspend fun getCurrentLocation(): LocationData {
        return locationManager.getLocation()
    }

    fun startLocationUpdates(
        intervalSeconds: Int,
        onLocationUpdate: (LocationData) -> Unit
    ){
        locationManager.listenLocation(intervalSeconds) { location ->
            log.d("Location" , "location ${location.latitude},${location.longitude}")
            onLocationUpdate(location)
        }
    }

    fun stopLocationListen()  {
        locationManager.stopLocationListen()
    }
}