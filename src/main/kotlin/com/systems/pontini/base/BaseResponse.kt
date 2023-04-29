package com.systems.pontini.base

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.systems.pontini.config.GENERIC_ERROR
import io.ktor.http.*

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
        val message: String? = GENERIC_ERROR,
        @JsonIgnore
        override val statusCode: HttpStatusCode = HttpStatusCode.BadRequest,
        val data: ErrorData = GenericError("kk", "aaaaa")
    ) : BaseResponse<Any>(statusCode)
}

interface ErrorData

data class GenericError(val nameError: String, val description: String) : ErrorData