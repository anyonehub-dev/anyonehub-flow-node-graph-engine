// Copyright 2024 anyone-Hub

import dev.shibasis.dependeasy.web.*
import dev.shibasis.dependeasy.android.*
import dev.shibasis.dependeasy.common.*
import dev.shibasis.dependeasy.server.*
import dev.shibasis.dependeasy.darwin.*

plugins {
    id("dev.shibasis.dependeasy.library")
}

val otelKotlinVersion = "0.1.0"
val firebaseKotlinVersion = "2.4.0"

kotlin {
    common {
        dependencies {
            api(project(":reaktor-core"))
            api(project(":reaktor-graph"))
            api("dev.gitlive:firebase-analytics:$firebaseKotlinVersion")
            api("io.opentelemetry.kotlin:api:$otelKotlinVersion")
            api("io.opentelemetry.kotlin:noop:$otelKotlinVersion")
            implementation("io.opentelemetry.kotlin:implementation:$otelKotlinVersion")
        }
    }
    droid {
        dependencies {
            implementation(project.dependencies.platform("com.google.firebase:firebase-bom:34.16.0"))
            api("dev.gitlive:firebase-crashlytics:$firebaseKotlinVersion")
        }
    }
    darwin {
        dependencies {
            api("dev.gitlive:firebase-crashlytics:$firebaseKotlinVersion")
        }
    }
    web {}
    server {}
}

android {
    defaults("dev.shibasis.reaktor.telemetry")
}
