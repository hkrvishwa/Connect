package org.connect.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.connect.chat.data.Screen
import org.connect.chat.domain.permission.PermissionStatus
import org.connect.chat.domain.permission.PermissionType
import org.connect.chat.domain.usecase.CheckPermissionUseCase
import org.connect.chat.domain.usecase.RequestPermissionUseCase
import org.connect.chat.platform.LocationData
import org.connect.chat.presentation.LocationViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route){
            MainMenu(navController)
        }

        composable(Screen.Permission.route){
            Permission(navController)
        }

        composable(Screen.Location.route){
            LocationScreen(navController)
        }

    }
}


@Composable
fun MainMenu(navController: NavHostController) {
    val features = listOf(
        "Permission",
        "LocationScreen",
        "B",
        "C",
        "D")

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(features.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowItems.forEach { item ->
                    Button(
                        onClick = {
                            when(item) {
                                "Permission" -> {
                                    navController.navigate(Screen.Permission.route)
                                }
                                "LocationScreen" -> {
                                    navController.navigate(Screen.Location.route)
                                }
                                else -> {

                                }
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(item)
                    }
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}



@Composable
fun Permission(navController: NavHostController) {
    val checkPermission: CheckPermissionUseCase = koinInject()
    val requestPermission: RequestPermissionUseCase = koinInject()

    var status by remember { mutableStateOf("Unknown") }

    Column {

        Column {

            Spacer(modifier = Modifier.height(50.dp))
            Button(onClick = {
                CoroutineScope(Dispatchers.Main).launch {
                    val granted = checkPermission(PermissionType.Camera)
                    status = if (granted) "Camera Granted" else "Camera Not Granted"
                }
            }) {
                Text("Check Camera permission")
            }

           /* PermissionType.Camera,
            PermissionType.LocationForeground,
            PermissionType.LocationBackground,*/
           /* PermissionType.RecordAudio*/

            Button(onClick = {
                CoroutineScope(Dispatchers.Main).launch {
                    val allPermissions = listOf(
                        PermissionType.LocationForeground,
                        PermissionType.Camera,
                        PermissionType.RecordAudio,
                        PermissionType.LocationBackground,
                    )

                    var result = true
                    for (permission in allPermissions) {
                        val permissionResponse = requestPermission(listOf(permission))
                        if (permissionResponse != PermissionStatus.Granted) {
                            result = false
                            break
                        }
                    }
                    if(result){
                        status = "permission granted"
                    }else{
                        status = "open app setting"
                    }
                }
            }) {
                Text("Request Permission")
            }

            Text(status)
        }
    }
}




@Composable
fun LocationScreen(
    navController: NavHostController
) {
    val viewModel: LocationViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Current Location Card
        state.currentLocation?.let { location ->
            LocationCard(location)
        }

        // Status
        Text(
            text = if (state.isTracking) "📍 Tracking Live" else "⏸️ Stopped",
            style = MaterialTheme.typography.headlineSmall,
            color = if (state.isTracking) Color.Green else Color.Gray
        )

        // Buttons
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = { viewModel.getCurrentLocation() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Get Current Location")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.startTracking(5) },
                    modifier = Modifier.weight(1f),
                    enabled = !state.isTracking
                ) {
                    Text("Start Tracking (5s)")
                }

                Button(
                    onClick = { viewModel.stopTracking() },
                    modifier = Modifier.weight(1f),
                    enabled = state.isTracking
                ) {
                    Text("Stop Tracking")
                }
            }
        }

        // Error
        state.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun LocationCard(location: LocationData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "📍 Current Location",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Lat: ${location.latitude}")
            Text("Lng: ${location.longitude}")
            Text("Accuracy: ${location.accuracy}m")
        }
    }
}