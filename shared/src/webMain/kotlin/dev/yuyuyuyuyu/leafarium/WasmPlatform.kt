package dev.yuyuyuyuyu.leafarium

class WasmPlatform : Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

fun getPlatform(): Platform = WasmPlatform()
