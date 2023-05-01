package com.systems.pontini.features.auth.presentation

import com.systems.pontini.core.BaseResponse
import com.systems.pontini.features.auth.domain.excpetion.UnauthorizedException
import com.systems.pontini.features.auth.domain.excpetion.UserExistException
import com.systems.pontini.features.auth.domain.excpetion.UserNotRegisteredException
import com.systems.pontini.features.auth.domain.usecase.LoginUserUseCase
import com.systems.pontini.features.auth.domain.usecase.RegisterUserUseCase
import com.systems.pontini.features.auth.presentation.model.CreateUserRequest
import com.systems.pontini.features.auth.presentation.model.USER_REGISTRATION_SUCCESS
import com.systems.pontini.features.auth.presentation.model.UserLoginRequest

class AuthController(private val registerUserUseCase: RegisterUserUseCase, private val  loginUserUseCase: LoginUserUseCase) {

    suspend fun onRegisterUser(createUserRequest: CreateUserRequest): BaseResponse<Any> {
        return try {
            val result = registerUserUseCase(createUserRequest).getOrThrow()
            BaseResponse.SuccessResponse(data = result, message = USER_REGISTRATION_SUCCESS)
        } catch (e: Exception) {
            when (e) {
                is UserNotRegisteredException -> {
                    BaseResponse.ErrorResponse(e.message)
                }
                is UserExistException -> {
                    BaseResponse.ErrorResponse(e.message)
                }
                else -> {
                    BaseResponse.ErrorResponse()
                }
            }
        }
    }

    suspend fun loginUser(params: UserLoginRequest): BaseResponse<Any> {
        return try {
            val result = loginUserUseCase(params)
             BaseResponse.SuccessResponse(result)
        }catch (e:Exception){
            when (e) {
                is UnauthorizedException -> {
                    BaseResponse.ErrorResponse(e.message)
                }
                else -> {
                    BaseResponse.ErrorResponse()
                }
            }
        }

    }
}

