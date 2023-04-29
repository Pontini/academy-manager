package com.systems.pontini

import com.systems.pontini.config.configureContentNegotiation
import com.systems.pontini.config.configureDatabase
import com.systems.pontini.config.configureRouting
import com.systems.pontini.config.configureStatusPages
import com.systems.pontini.security.configureSecurity
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureDatabase()
    configureContentNegotiation()
    configureStatusPages()
    configureSecurity()
    configureRouting()
}
