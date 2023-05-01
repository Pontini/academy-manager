package com.systems.pontini.core.security

import com.systems.pontini.core.BaseResponse
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

private const val INVALID_AUTHENTICATION_TOKEN = "Invalid authentication token, please login again"

fun Application.configureSecurity(){
    JwtConfig.initialize("manager-academy")
    install(Authentication){
        jwt {
            verifier(JwtConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JwtConfig.CLAIM).asInt()
                if(claim != null){
                    UserIdPrincipalForUser(claim)
                }else {
                    null
                }
            }
            challenge { _, _ ->
                call.respond(BaseResponse.ErrorResponse(INVALID_AUTHENTICATION_TOKEN))
            }
        }
    }
}