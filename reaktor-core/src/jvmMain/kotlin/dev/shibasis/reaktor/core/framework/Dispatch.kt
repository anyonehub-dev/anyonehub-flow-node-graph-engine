// Copyright 2024 anyone-Hub

package dev.shibasis.reaktor.core.framework


import kotlinx.coroutines.Dispatchers

actual val Dispatchers.Async get() = IO