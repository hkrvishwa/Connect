package org.connect.chat.data



sealed class Screen(val route: String) {

    object Home : Screen("HOME")
    object Permission : Screen("PERMISSION")

    object Location : Screen("LOCATION")

    object Detail : Screen("detail")

    object Profile : Screen("profile/{userId}") {

        fun createRoute(userId: String): String {
            return "profile/$userId"
        }
    }
}