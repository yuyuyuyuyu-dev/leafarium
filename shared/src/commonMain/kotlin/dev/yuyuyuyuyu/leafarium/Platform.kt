package dev.yuyuyuyuyu.leafarium

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
