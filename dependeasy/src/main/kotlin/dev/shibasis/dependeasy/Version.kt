// Copyright 2024 anyone-Hub

package dev.shibasis.dependeasy

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Version {
    object SDK {
        const val minSdk = 34
        const val compileSdk = 37
        const val targetSdk = 37
        const val ndkVersion = "30.0.15729638"
        const val CMake = "4.3.1"

        const val targetDarwin = "13"

        const val Kotlin = "2.4.0"
        const val SpringBoot = "4.0.0"

        // JVM (server, desktop, toolchain) — Java 21
        object Java {
            val asTarget = JvmTarget.JVM_21
            val asEnum = JavaVersion.VERSION_21
            val asString = "21"
            val asInt = asString.toInt()
        }

        // Android — stays at 21 (AGP toolchain constraint)
        object AndroidJava {
            val asTarget = JvmTarget.JVM_21
            val asEnum = JavaVersion.VERSION_21
        }
    }

    // Android
    const val Activity = "1.13.0"
    const val Fragment = "1.8.5"
    const val Lifecycle = "2.11.0"
    const val Navigation = "2.11.0"

    // Web
    const val KotlinJSWrappers = "2025.10.4"

    // Data
    const val SQLDelight = "2.0.0"
    const val Exposed = "1.0.0-beta-2"
    const val OkHttp = "4.12.0"
    const val WorkManager = "2.9.0"
    const val Ktor = "3.5.1"
    const val Koin = "4.1.0"
    const val KoinAnnotations = "2.0.0"

    // Cloud
    const val Firebase = "34.16.0"

    // Android Camera
    const val CameraX = "1.5.2"

    // KMM Async
    const val Coroutines = "1.11.0"
    const val Kermit = "2.0.5"
    const val Serialization = "1.11.0"
    const val Seskar = "4.25.0"

    // DevTools
    const val LeakCanary = "2.8.1"
    const val SoLoader = "0.10.1"

    val architectures = listOf(
//        "armeabi-v7a",
        "x86",
        "arm64-v8a",
        "x86_64"
    )
    val nativeLibraries = listOf(
        "libc++_shared.so",
        "libreactnativejni.so",
        "libfbjni.so",
        "libfolly_runtime.so",
        "libglog.so",
        "libjsi.so",
        // todo stupid hack fix
        "libhermes.so",
        "libReaktorFlexbuffer.so",
        "libReaktorFFI.so",
        "libFlatInvokerReact.so"
    )
}
