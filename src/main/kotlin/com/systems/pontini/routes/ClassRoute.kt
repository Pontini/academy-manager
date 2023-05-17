package com.systems.pontini.routes

import com.systems.pontini.features.auth.presentation.AuthController
import com.systems.pontini.features.auth.presentation.model.CreateUserRequest
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.route
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject

fun Application.classRoutes() {

    val authController by inject<AuthController>()

    routing {
        route("/class") {
            post("/register-presence") {
                val params = call.receive<CreateUserRequest>()
                val result = authController.onRegisterUser(params)
                call.respond(result.statusCode, result)
            }
        }
    }
}