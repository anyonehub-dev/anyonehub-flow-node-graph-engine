// Copyright 2024 anyone-Hub

import dev.shibasis.dependeasy.*
import dev.shibasis.dependeasy.android.*
import dev.shibasis.dependeasy.common.*
import dev.shibasis.dependeasy.darwin.*


plugins {
    id("dev.shibasis.dependeasy.library")
    
}

group = "dev.shibasis.flatinvoker.react"
version = "1.0-SNAPSHOT"

dependeasy {
    androidNative {
        fbjni()
    }
}

kotlin {
    common {
        dependencies {
            api(project(":reaktor-core"))
            api(project(":reaktor-io"))
            // api(project(":flatinvoker-core"))
        }
    }

    droid {
        dependencies {
            api("com.facebook.react:react-android:0.71.19") {
                exclude(module = "fbjni-java-only")
            }
        }
    }

    darwin {

    }
}


configure<com.android.build.api.dsl.LibraryExtension> {
    defaults("dev.shibasis.flatinvoker.react")
}
