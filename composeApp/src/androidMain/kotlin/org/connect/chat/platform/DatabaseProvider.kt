package org.connect.chat.platform

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.connect.chat.db.AppDatabase
import org.connect.chat.AppContext


/*actual fun getDatabase()  : AppDatabase  {
    val context = AppContext.instance
    val driver = AndroidSqliteDriver(
        schema = AppDatabase.Schema,
        context = context,
        name = "app.db"
    )
    return AppDatabase(driver)
}*/


class AndroidDriverFactory(
    private val context: Context
) : DatabaseDriverFactory {

    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            AppDatabase.Schema,
            context,
            "app.db"
        )
    }
}