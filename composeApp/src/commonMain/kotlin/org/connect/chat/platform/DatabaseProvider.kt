package org.connect.chat.platform

import app.cash.sqldelight.db.SqlDriver
import com.connect.chat.db.AppDatabase


/*expect fun getDatabase(): AppDatabase*/


interface DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(factory: DatabaseDriverFactory): AppDatabase {
    return AppDatabase(factory.createDriver())
}