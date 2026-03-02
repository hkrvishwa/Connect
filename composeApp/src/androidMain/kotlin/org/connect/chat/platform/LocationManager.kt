package org.connect.chat.platform

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.Job
import org.koin.mp.KoinPlatform.getKoin
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

actual fun getLocationManager(): LocationManager = getKoin().get()


class AndroidLocationManager(
    private val context: Context
) : LocationManager {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private var locationCallback: LocationCallback? = null
    private var locationJob: Job? = null

    private fun ensurePermissions(): Boolean {
        val finePermission = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED

        val coarsePermission = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED

        return finePermission || coarsePermission
    }

    @SuppressLint("MissingPermission")
    override suspend fun getLocation(): LocationData = suspendCoroutine { cont ->
        if (!ensurePermissions()) {
            cont.resumeWithException(SecurityException("Location permission required"))
            return@suspendCoroutine
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    cont.resume(
                        LocationData(
                            latitude = location.latitude,
                            longitude = location.longitude,
                            accuracy = location.accuracy,
                        )
                    )
                } else {
                    cont.resumeWithException(Exception("No location available"))
                }
            }
            .addOnFailureListener {
                cont.resumeWithException(it)
            }
    }

    @SuppressLint("MissingPermission")
    override  fun listenLocation(timeInSec: Int, locationCallback: (LocationData) -> Unit) {
        if (!ensurePermissions()) {
            throw SecurityException("Location permission required")
        }

        val interval = (timeInSec * 1000).toLong()
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, interval)
            .setMinUpdateIntervalMillis(interval / 2)
            .build()

        this.locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.locations.forEach { location ->
                    locationCallback(
                        LocationData(
                            latitude = location.latitude,
                            longitude = location.longitude,
                            accuracy = location.accuracy
                        )
                    )
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            this.locationCallback!!,
            null
        )
    }

    override  fun stopLocationListen() {
        locationCallback?.let { callback ->
            fusedLocationClient.removeLocationUpdates(callback)
        }
        locationCallback = null
    }
}