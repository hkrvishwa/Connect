package org.connect.chat.data

import org.connect.chat.platform.Logger

class LogRepo(
    val log: Logger
) {

    fun d(TAG: String,message:String){
       log.d(TAG,message)
    }

    fun e(TAG: String,message:String){
        log.e(TAG,message)
    }
}