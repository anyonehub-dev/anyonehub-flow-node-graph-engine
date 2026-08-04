// Copyright 2024 anyone-Hub

package dev.shibasis.reaktor.core.framework


actual sealed interface ControllerEventObserver {
    actual fun handle(event: ControllerEvent)
}
