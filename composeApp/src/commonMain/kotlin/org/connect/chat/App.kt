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
import androidx.compose.material3.OutlinedTextField
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
import org.connect.chat.composables.CustomTextInput
import org.connect.chat.composables.CustomTextInput2
import org.connect.chat.composables.CustomTextInput3
import org.connect.chat.data.Screen
import org.connect.chat.domain.permission.PermissionStatus
import org.connect.chat.domain.permission.PermissionType
import org.connect.chat.domain.usecase.CheckPermissionUseCase
import org.connect.chat.domain.usecase.RequestPermissionUseCase
import org.connect.chat.platform.LocationData
import org.connect.chat.presentation.LocationViewModel
import org.connect.chat.presentation.NoteViewModel
import org.connect.chat.presentation.UserViewModel
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

        composable(Screen.SQLNote.route){
            SQLNote(navController)
        }

        composable(Screen.UIScreen.route){
            UIScreen(navController)
        }

        composable(Screen.Ktor.route){
            Ktor(navController)
        }

    }
}


@Composable
fun MainMenu(navController: NavHostController) {
    val features = listOf(
        "Permission",
        "LocationScreen",
        "SQLNote",
        "UIScreen",
        "Ktor")

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
                                "SQLNote" -> {
                                    navController.navigate(Screen.SQLNote.route)
                                }
                                "UIScreen" -> {
                                    navController.navigate(Screen.UIScreen.route)
                                }
                                "Ktor" -> {
                                    navController.navigate(Screen.Ktor.route)
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


@Composable
private fun SQLNote(navController: NavHostController){
    val viewModel: NoteViewModel = koinInject()

    val notes by viewModel.notes.collectAsState()

    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Type something") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                viewModel.addNote(text)
                text = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(notes) { note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = note.message,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun UIScreen(navController: NavHostController){

    var text by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var text3 by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize()
    ){

        Spacer(modifier = Modifier.height(20.dp))
        CustomTextInput(
            value = text,
            onValueChange = { text = it },
            placeholder = "Enter your text"
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextInput2(
            value = text2,
            onValueChange = { text2 = it },
            placeholder = "Enter your text"
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextInput3(
            value = text3,
            onValueChange = { text3 = it },
            placeholder = "Enter your text"
        )

       /* OutlinedTextField(
            value = text,
            onValueChange = { text = it }
        )*/


    }
}

@Composable
private fun Ktor(navController: NavHostController){
    val viewModel : UserViewModel = koinViewModel()

    val userData = viewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getUsers()
    }

    when{
        userData.value.isLoading == true -> {
            CircularProgressIndicator()
        }

        userData.value.error?.isNotEmpty() == true -> {

        }

        userData.value.userList?.isNullOrEmpty() == false -> {
            val userlistData = userData.value.userList
            userlistData?.let { users ->
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = users,
                        key = { user -> user.id },
                    ) { user ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Text(
                                text = user.name,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }

    }
}