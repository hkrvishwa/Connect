package org.connect.chat


object AppContext {
    lateinit var instance: android.content.Context
        private set

    fun init(context: android.content.Context) {
        instance = context.applicationContext
    }
}