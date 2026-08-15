// Copyright 2024 anyone-Hub

package dev.shibasis.dependeasy.tasks

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider

fun Project.droidCmake(
    abi: String,
    sdkDir: String,
    minSdk: Int = dev.shibasis.dependeasy.Version.SDK.minSdk,
    stl: String = "c++_shared"
): TaskProvider<out Task>? {
    val ndkDir = "$sdkDir/ndk/${dev.shibasis.dependeasy.Version.SDK.ndkVersion}"
    val isWindows = System.getProperty("os.name").contains("Windows", ignoreCase = true)
    val ext = if (isWindows) ".exe" else ""
    val cmakePath = "cmake$ext"
    val ninjaPath = "ninja$ext"
    return kotlinCmake(CmakePlatform.Android(abi, ndkDir, cmakePath, ninjaPath, minSdk, stl))
}
