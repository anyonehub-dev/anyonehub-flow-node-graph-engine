// Copyright 2024 anyone-Hub

@file:Suppress("KotlinJniMissingFunction")

package dev.shibasis.flatinvoker.react.types

expect class NoArgNativeFunction {
    operator fun invoke(): Any
}


expect class SingleArgNativeFunction {
    operator fun invoke(data: Any): Any
}
