// Copyright 2024 anyone-Hub

package dev.shibasis.dependeasy.plugins

import dev.shibasis.dependeasy.settings.NativeBootstrap
import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings

class SettingsPlugin: Plugin<Settings> {
    override fun apply(target: Settings) {
        NativeBootstrap(target).bootstrap()
    }
}
