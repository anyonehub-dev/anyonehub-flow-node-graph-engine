// Copyright 2024 anyone-Hub

package dev.shibasis.reaktor.io.network


import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js

actual val http = HttpClient(Js) {
    middleware()
}