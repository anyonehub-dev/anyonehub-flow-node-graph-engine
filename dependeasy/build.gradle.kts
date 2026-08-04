// Copyright 2024 anyone-Hub

val kotlinVersion = "2.4.0"

plugins {
    id("java-gradle-plugin")
    id("maven-publish")
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    maven(url = "https://plugins.gradle.org/m2/")
    maven(url = "https://jitpack.io")
}

dependencies {
    // Align Version of all Kotlin components
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("com.android.tools.build:gradle:9.3.1") {
        exclude(group = "org.apache.commons", module = "commons-compress")
    }
    implementation("org.apache.commons:commons-compress:1.28.0") // todo remember to upgrade on upgrading Spring
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")
    implementation("com.google.firebase:firebase-crashlytics-gradle:3.0.7")
    implementation("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.22.0")
    implementation("com.google.devtools.ksp:symbol-processing-gradle-plugin:2.3.9")
    implementation("com.github.node-gradle:gradle-node-plugin:7.1.0")
    implementation("io.github.turansky.seskar:seskar-gradle-plugin:4.25.0")

}

gradlePlugin {
    val libraryPlugin by plugins.creating {
        id = "dev.shibasis.dependeasy.library"
        implementationClass = "dev.shibasis.dependeasy.plugins.LibraryPlugin"
    }

    val applicationPlugin by plugins.creating {
        id = "dev.shibasis.dependeasy.application"
        implementationClass = "dev.shibasis.dependeasy.plugins.ApplicationPlugin"
    }

    val settingsPlugin by plugins.creating {
        id = "dev.shibasis.dependeasy.settings"
        implementationClass = "dev.shibasis.dependeasy.plugins.SettingsPlugin"
    }
}

kotlin {
//    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs.addAll("-Xcontext-parameters")
    }
}

tasks.named<Jar>("jar") {
    archiveFileName.set("dependeasy.jar")
    from(sourceSets.main.get().output)
}

apply(from = "$rootDir/../publishing.gradle.kts")
