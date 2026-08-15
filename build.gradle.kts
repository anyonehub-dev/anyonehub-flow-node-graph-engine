// Copyright 2024 anyone-Hub

import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnLockMismatchReport
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension
import java.time.LocalDate

plugins {
    kotlin("multiplatform") apply false
    kotlin("android") apply false
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("com.google.firebase.crashlytics") apply false
    id("org.jetbrains.compose") apply false
    id("org.jetbrains.kotlin.plugin.compose").apply(false)
    id("com.google.devtools.ksp") apply false
    id("dev.shibasis.dependeasy.library") apply false
    id("dev.shibasis.dependeasy.application") apply false
    id("org.jetbrains.kotlinx.benchmark") apply false
    id("com.codingfeline.buildkonfig") apply false
    id("org.jetbrains.dokka") version "2.0.0"
    id("com.vanniktech.maven.publish") version "0.37.0" apply false
}

buildscript {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

allprojects {
    group = "dev.shibasis.reaktor"
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        maven(url = "https://www.jitpack.io")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
    }
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "com.vanniktech.maven.publish")

    tasks.matching { it.name.startsWith("dokka") }.configureEach {
        dependsOn(tasks.matching { it.name.startsWith("ksp") })
    }

    afterEvaluate {
        configure<com.vanniktech.maven.publish.MavenPublishBaseExtension> {
            coordinates("com.anyonehub", project.name, "1.0.0")

            pom {
                name.set("Anyone-Hub Reaktor Engine")
                description.set("The core engine powering the anyone-Hub flow node graph components.")
                inceptionYear.set("2024")
                url.set("https://github.com/anyonehub-dev/anyonehub-flow-node-graph-engine")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("anyone-Hub")
                        name.set("Thomas Alan Slinkard Jr")
                        url.set("https://github.com/anyonehub-dev")
                    }
                }
                scm {
                    url.set("https://github.com/anyonehub-dev/anyonehub-flow-node-graph-engine")
                    connection.set("scm:git:git://github.com/anyonehub-dev/anyonehub-flow-node-graph-engine.git")
                    developerConnection.set("scm:git:ssh://git@github.com/anyonehub-dev/anyonehub-flow-node-graph-engine.git")
                }
            }

            publishToMavenCentral()
            if (project.hasProperty("signing.keyId")) {
                signAllPublications()
            }
        }

        // Disable lint for library modules — lint runs on the app target, not here
        tasks.matching { it.name.contains("lint", ignoreCase = true) }.configureEach {
            enabled = false
        }
    }
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
    compilerOptions {
        freeCompilerArgs.add("-Xopt-in=kotlin.experimental.ExperimentalNativeApi")
    }
}

tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(file("docs"))
}


tasks.register("publishToGithubPackages") {
    group = "reaktor"
    dependsOn(gradle.includedBuild("dependeasy").task(":publish"))
    dependsOn(subprojects.mapNotNull { it.tasks.findByName("publish") })
}

//tasks.register("publishToMavenCentral") {
//    group = "reaktor"
//    version = LocalDate.now().run { "$year.$monthValue.$dayOfMonth" }
//    dependsOn(gradle.includedBuild("dependeasy").task(":publishAllPublicationsToMavenCentralRepository"))
//    dependsOn(subprojects.mapNotNull { it.tasks.findByName("publishAllPublicationsToMavenCentralRepository") })
//}


tasks.register("publishToMavenLocal") {
    group = "reaktor"
    dependsOn(gradle.includedBuild("dependeasy").task(":publishToMavenLocal"))
    dependsOn(subprojects.mapNotNull { it.tasks.findByName("publishToMavenLocal") })
}


rootProject.plugins.withType(YarnPlugin::class.java) {
    rootProject.extensions.configure(YarnRootExtension::class.java) {
        // "FAIL" ensures CI breaks if the lockfile drifts, preventing phantom dependency issues.
        yarnLockMismatchReport = YarnLockMismatchReport.WARNING

        // Set to FALSE on CI (via System.getenv("CI")) to ensure reproducible builds.
        reportNewYarnLock = false
        yarnLockAutoReplace = true
    }
}

subprojects {
    tasks.withType<PublishToMavenLocal>().configureEach {
        doFirst {
            val metadataDir = layout.buildDirectory.dir("kotlinToolingMetadata").get().asFile
            val metadataFile = File(metadataDir, "kotlin-tooling-metadata.json")
            if (!metadataFile.exists()) {
                metadataDir.mkdirs()
                metadataFile.writeText("{}")
            }
        }
    }
}