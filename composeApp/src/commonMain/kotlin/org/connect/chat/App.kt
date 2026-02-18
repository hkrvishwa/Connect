package org.connect.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.connect.chat.data.Screen

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


    }
}


@Composable
fun MainMenu(navController: NavHostController) {
    val features = listOf(
        "Permission",
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
    Text("Permission screen")
}