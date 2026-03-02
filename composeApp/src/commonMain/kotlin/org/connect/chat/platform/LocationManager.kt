package org.connect.chat.platform

data class LocationData(
    val latitude : Double? = null,
    val longitude : Double? = null,
    val accuracy : Float? = null,
    val isMock : Boolean = true
)

interface LocationManager {

    suspend fun getLocation() : LocationData

     fun listenLocation(timeInSec : Int,
                               location : (LocationData) -> Unit)
     fun stopLocationListen()
}

expect fun getLocationManager() : LocationManager