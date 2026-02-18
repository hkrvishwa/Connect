package org.connect.chat

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform