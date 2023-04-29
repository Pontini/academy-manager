package com.systems.pontini.security

import com.systems.pontini.base.BaseResponse
import com.systems.pontini.config.INVALID_AUTHENTICATION_TOKEN
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*


fun Application.configureSecurity(){
    JwtConfig.initialize("my-story-app")
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