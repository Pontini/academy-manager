package com.systems.pontini.routes

import com.systems.pontini.features.auth.presentation.AuthController
import com.systems.pontini.features.auth.presentation.model.CreateUserRequest
import com.systems.pontini.features.auth.presentation.model.UserLoginRequest
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.route
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject

fun Application.authRoutes() {

    val authController by inject<AuthController>()

    routing {
        route("/auth") {
            post("/register") {
                val params = call.receive<CreateUserRequest>()
                val result = authController.onRegisterUser(params)
                call.respond(result.statusCode, result)
            }

            post("/login") {
               val params = call.receive<UserLoginRequest>()
                val result = authController.loginUser(params)
                call.respond(result.statusCode, result)
            }
        }
    }
}