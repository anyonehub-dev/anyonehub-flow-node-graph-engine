// Copyright 2024 anyone-Hub

package dev.shibasis.reaktor.flexbuffer

internal object NativeFlexBridge {
    init {
        System.loadLibrary("ReaktorFlexbuffer")
    }

    external fun flexHello(): ByteArray
}

actual fun nativeFlexBufferBytes(): ByteArray = NativeFlexBridge.flexHello()
