// Copyright 2024 anyone-Hub

package dev.shibasis.reaktor.location

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val longitude: Double,
    val latitude: Double
)

