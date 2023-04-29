package com.systems.pontini.routes.auth

import com.systems.pontini.base.BaseResponse
import com.systems.pontini.base.GenericError
import com.systems.pontini.domain.usecase.RegisterUserUseCase
import com.systems.pontini.routes.CreateUserRequest

class AuthController(private val registerUserUseCase: RegisterUserUseCase) {

    suspend fun onRegisterUser(createUserRequest: CreateUserRequest): BaseResponse<Any> {
        return try {
            val result  = registerUserUseCase(createUserRequest).getOrThrow()
            BaseResponse.SuccessResponse(data = result, message = "Usuário registrado com sucesso.")

        } catch (e: Exception) {
            BaseResponse.ErrorResponse(data = GenericError(nameError = "OUTRO ERRRO", description = "vamo ver a mesa"+e.message))
        }
    }
}

