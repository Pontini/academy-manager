package com.systems.pontini.routes

import com.systems.pontini.routes.auth.AuthController
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(authController: AuthController) {
    routing {
        route("/auth") {
            post("/register") {
                val params = call.receive<CreateUserRequest>()
                val result = authController.onRegisterUser(params)
                print("AAAAA-> "+result)
                call.respond(result.statusCode, result)
            }

            post("/login") {
             /*   val params = call.receive<UserLoginParams>()
                val result = repository.loginUser(params)
                call.respond(result.statusCode, result)*/
            }
        }
    }
}