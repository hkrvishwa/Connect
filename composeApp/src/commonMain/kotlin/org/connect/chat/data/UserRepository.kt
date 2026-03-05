package org.connect.chat.data

import kotlinx.serialization.Serializable
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.connect.chat.platform.Logger

class UserRepository(
    private val logger: Logger,
    private val client: HttpClient) {

     suspend fun getUsers() = withContext(Dispatchers.IO){
         logger.d("UserRepository","api call")
         client.get("https://jsonplaceholder.typicode.com/users")
            .body<List<User>>()
    }
}

@Serializable
data class User(
    val id: Int,
    val name: String,
    val email: String
)