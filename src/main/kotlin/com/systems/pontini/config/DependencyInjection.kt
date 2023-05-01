package com.systems.pontini.config

import com.systems.pontini.features.auth.di.authModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDependencyInjection() {
    install(Koin) {
        slf4jLogger()
        modules(authModule)
    }

}
