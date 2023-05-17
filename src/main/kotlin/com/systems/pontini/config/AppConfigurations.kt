package com.systems.pontini.config

import com.systems.pontini.features.auth.data.DatabaseFactory
import io.ktor.serialization.jackson.jackson
import io.ktor.server.application.install
import io.ktor.server.application.Application
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

fun configureDatabase() {
    DatabaseFactory.init()
}

fun Application.configureContentNegotiation() {
    install(ContentNegotiation) {
        jackson()
    }
}
