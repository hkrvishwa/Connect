package org.connect.chat.platform

interface Logger{

    fun d(TAG: String,message:String)

    fun e(TAG: String,message:String)
}

expect fun getLog() : Logger