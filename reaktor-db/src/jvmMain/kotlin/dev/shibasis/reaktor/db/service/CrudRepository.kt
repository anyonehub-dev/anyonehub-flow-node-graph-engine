// Copyright 2024 anyone-Hub

package dev.shibasis.reaktor.db.service

import dev.shibasis.reaktor.service.Request

abstract class CrudRepository(
    val adapter: ExposedAdapter
) {
    suspend fun<T> Request.sql(
        statement: () -> T?
    ): Result<T> = adapter.sql(environment, statement)
}
