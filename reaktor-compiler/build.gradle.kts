// Copyright 2024 anyone-Hub

plugins {
    kotlin("jvm")
}

group = "dev.shibasis.reaktor"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":reaktor-core"))
    implementation("com.squareup:kotlinpoet:2.3.0")
    implementation("com.squareup:kotlinpoet-ksp:2.3.0")
    implementation("com.google.devtools.ksp:symbol-processing-api:2.3.7")
    testImplementation(kotlin("test"))
}

sourceSets.main {
    kotlin.srcDirs("src/main/kotlin")
}
