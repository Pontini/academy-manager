package com.systems.pontini.config

import com.systems.pontini.data.DatabaseFactory
import com.systems.pontini.di.RepositoryProvider
import com.systems.pontini.routes.authRoutes
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun configureDatabase() {
    DatabaseFactory.init()
}

fun Application.configureContentNegotiation() {
    install(ContentNegotiation) {
        jackson()
    }
}

fun Application.configureRouting(){
    authRoutes(RepositoryProvider.provideAuthController())
}