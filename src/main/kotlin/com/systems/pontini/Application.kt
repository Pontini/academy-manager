package com.systems.pontini

import com.systems.pontini.config.configureContentNegotiation
import com.systems.pontini.config.configureDatabase
import com.systems.pontini.config.configureDependencyInjection
import com.systems.pontini.config.configureStatusPages
import com.systems.pontini.core.security.configureSecurity
import com.systems.pontini.routes.authRoutes
import com.systems.pontini.routes.classRoutes
import com.systems.pontini.routes.reportRoutes
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

private const val HOST = "0.0.0.0"
private const val PORT = 8080

fun main() {
    embeddedServer(Netty, port = PORT, host = HOST , module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureDependencyInjection()
    configureDatabase()
    configureContentNegotiation()
    configureStatusPages()
    configureSecurity()
    authRoutes()
    reportRoutes()
    classRoutes()
}
