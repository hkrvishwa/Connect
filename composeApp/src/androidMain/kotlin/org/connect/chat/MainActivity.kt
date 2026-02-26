package org.connect.chat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.connect.chat.platform.AndroidPermissionManager
import org.connect.chat.platform.PermissionManager
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {

    private val permissionManager: PermissionManager by inject()

    // Pre-register ONCE in onCreate
  /*  private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        (permissionManager as AndroidPermissionManager).onPermissionResult(isGranted)
    }*/

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        (permissionManager as AndroidPermissionManager).onMultiplePermissionResult(permissionsMap)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        (permissionManager as AndroidPermissionManager).apply {
            currentActivity = this@MainActivity
            launcher = permissionLauncher  // Pass the launcher
        }
        setContent {
            App()
        }
    }

    override fun onDestroy() {

        super.onDestroy()
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}