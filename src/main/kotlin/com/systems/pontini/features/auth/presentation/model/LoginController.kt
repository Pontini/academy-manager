package com.systems.pontini.features.auth.presentation.model

import com.systems.pontini.core.BaseResponse
import com.systems.pontini.features.auth.domain.excpetion.UnauthorizedException
import com.systems.pontini.features.auth.domain.usecase.LoginUserUseCase

class LoginController(private val  loginUserUseCase: LoginUserUseCase) {

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

