package org.connect.chat.platform

import org.koin.mp.KoinPlatform.getKoin
import android.util.Log

actual fun getLog() : Logger  = getKoin().get()

class AndroidLog()  : Logger{

    override fun d(TAG: String, message: String) {
        Log.d(TAG,message)
    }

    override fun e(TAG: String, message: String) {
        Log.e(TAG,message)
    }
}