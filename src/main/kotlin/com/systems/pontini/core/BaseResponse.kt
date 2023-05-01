package com.systems.pontini.core

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.ktor.http.HttpStatusCode

private const val GENERIC_ERROR_DESCRIPTION = "Ocorreu algum erro! Por favor, tente novamente mais tarde"
private const val GENERIC_ERROR_TITLE = "Erro inesperado!"

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize
sealed class BaseResponse<T>(
    @JsonIgnore open val statusCode: HttpStatusCode
) {
    @JsonSerialize
    data class SuccessResponse<T>(
        val data: T?,
        val message: String? = null,
        @JsonIgnore
        override val statusCode: HttpStatusCode = HttpStatusCode.OK
    ) : BaseResponse<T>(statusCode)

    @JsonSerialize
    data class ErrorResponse(
        @JsonIgnore
        val message: String? = GENERIC_ERROR_DESCRIPTION,
        @JsonIgnore
        override val statusCode: HttpStatusCode = HttpStatusCode.InternalServerError,
        val data: ErrorData = GenericErrorData(GENERIC_ERROR_TITLE, message?: GENERIC_ERROR_DESCRIPTION)
    ) : BaseResponse<Any>(statusCode)
}

interface ErrorData

data class GenericErrorData(val title: String, val description: String) : ErrorData