// Copyright 2024 anyone-Hub

package dev.shibasis.reaktor.core.framework


import js.coroutines.promise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

actual val Dispatchers.Async get() = Default