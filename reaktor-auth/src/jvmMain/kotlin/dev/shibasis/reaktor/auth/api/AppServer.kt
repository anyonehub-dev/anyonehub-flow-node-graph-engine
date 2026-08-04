// Copyright 2024 anyone-Hub

package dev.shibasis.reaktor.auth.api

class AppServer(
    private val graphService: AppService,
) : AppService("") {
    override val getAll = graphService.getAll
    override val getApp = graphService.getApp

    init {
        handlers += listOf(getAll, getApp)
    }
}
