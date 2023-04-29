package com.systems.pontini.config

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.systems.pontini.base.BaseResponse
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            val error = when(cause){
                is MissingKotlinParameterException -> BaseResponse.ErrorResponse("Missing attribute `${cause.parameter.name}`")
                else -> BaseResponse.ErrorResponse(cause.message.orGenericMessage())
            }
            call.respond(error.statusCode, error)

        }
        exception<JsonParseException> { call, cause ->
            call.respond(BaseResponse.ErrorResponse(cause.message.orGenericMessage()))
        }
    }
}

internal fun String?.orGenericMessage():String{
    if(this.isNullOrEmpty()){
        return GENERIC_ERROR
    }
    return this
}