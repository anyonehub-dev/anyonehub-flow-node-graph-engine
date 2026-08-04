// Copyright 2024 anyone-Hub

package dev.shibasis.reaktor.core.utils

import kotlin.time.Clock


fun epochTime() = Clock.System.now().toEpochMilliseconds()
