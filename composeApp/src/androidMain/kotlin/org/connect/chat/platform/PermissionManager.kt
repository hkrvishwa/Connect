package org.connect.chat.platform

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import org.connect.chat.domain.permission.PermissionStatus
import org.connect.chat.domain.permission.PermissionType
import org.koin.core.context.GlobalContext


actual fun getPermissionManager(): PermissionManager {
    return return GlobalContext.get().get()
}


class AndroidPermissionManager(
) : PermissionManager {

    @Volatile
    lateinit var currentActivity: ComponentActivity

    lateinit var launcher: ActivityResultLauncher<Array<String>>
    private var pendingContinuation: CancellableContinuation<PermissionStatus>? = null

    override suspend fun isPermissionGranted(
        permission: PermissionType
    ): Boolean {
        return ContextCompat.checkSelfPermission(
            currentActivity,
            mapPermission(permission)
        ) == PackageManager.PERMISSION_GRANTED
    }
    private var pendingMultiPermissions: List<PermissionType>? = null


    override suspend fun requestPermission(permissions: List<PermissionType>): PermissionStatus {
        val permStrings = permissions.map { mapPermission(it) }.toTypedArray()

        return suspendCancellableCoroutine { continuation ->
            pendingContinuation = continuation
            pendingMultiPermissions = permissions
            launcher?.launch(permStrings) ?: error("Launcher not set")
        }
    }

    fun onMultiplePermissionResult(permissionsMap: Map<String, Boolean>) {
        val continuation = pendingContinuation ?: return
        val permission = pendingMultiPermissions ?: return
        pendingContinuation = null
        pendingMultiPermissions = null

        val activity = currentActivity ?: run {
            continuation.resume(PermissionStatus.Denied) {}
            return
        }

        val allGranted = permissionsMap.values.all { it }  // true only if ALL true

        if (allGranted) {
            continuation.resume(PermissionStatus.Granted){}
            return
        }

        val hasPermanentlyDenied = permissionsMap.any { (permString, granted) ->
            !granted && !ActivityCompat.shouldShowRequestPermissionRationale(activity, permString)
        }

        val result = if (hasPermanentlyDenied) {
            PermissionStatus.PermanentlyDenied
        } else {
            PermissionStatus.Denied  // Some denied, but can show rationale
        }

        continuation.resume(result){}

        /*val result = when {
            granted -> PermissionStatus.Granted
            ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
                -> PermissionStatus.Denied
            else -> PermissionStatus.PermanentlyDenied
        }*/
    }


    private fun mapPermission(type: PermissionType): String =
        when (type) {
            PermissionType.Camera -> Manifest.permission.CAMERA
            PermissionType.LocationForeground ->
                Manifest.permission.ACCESS_FINE_LOCATION
            PermissionType.LocationBackground ->
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
        }
}


