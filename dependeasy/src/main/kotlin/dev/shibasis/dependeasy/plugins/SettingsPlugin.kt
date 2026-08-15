// Copyright 2024 anyone-Hub

package dev.shibasis.dependeasy.plugins

import dev.shibasis.dependeasy.settings.NativeBootstrap
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class SettingsPlugin: Plugin<Settings> {
    override fun apply(target: Settings) {
        val skip = target.providers.gradleProperty("skipNativeBootstrap").orNull == "true"
            || System.getProperty("skipNativeBootstrap") == "true"
        if (!skip) {
            NativeBootstrap(target).bootstrap()
        }
    }
}
