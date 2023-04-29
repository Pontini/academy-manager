package com.systems.pontini.data.repository

import com.systems.pontini.base.BaseResponse
import com.systems.pontini.data.service.auth.AuthDataSource
import com.systems.pontini.domain.model.User
import com.systems.pontini.domain.repository.AuthRepository
import com.systems.pontini.routes.CreateUserRequest
import com.systems.pontini.routes.UserLoginParams


class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource,
) : AuthRepository {
    /*    override suspend fun registerUser(params: CreateUserParams): BaseResponse<Any> {
        return if (isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(message = MESSAGE_EMAIL_ALREADY_REGISTERED)
        } else {
            val user = authDataSource.registerUser(params)
            if (user != null) {
                val token = JwtConfig.instance.createAccessToken(user.id)
                user.authToken = token
                BaseResponse.SuccessResponse(data = user, message = USER_REGISTRATION_SUCCESS)
            } else {
                BaseResponse.ErrorResponse(GENERIC_ERROR)
            }
        }
    }

    override suspend fun loginUser(params: UserLoginParams): BaseResponse<Any> {
        val user = authDataSource.loginUser(params.email, params.password)
        return if (user != null) {
            val token = JwtConfig.instance.createAccessToken(user.id)
            user.authToken = token
            BaseResponse.SuccessResponse(data = user, message = USER_LOGIN_SUCCESS)
        } else {
            BaseResponse.ErrorResponse(USER_LOGIN_FAILURE)
        }
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return authDataSource.findUserByEmail(email) != null
    }*/
    override suspend fun registerUser(params: CreateUserRequest): Result<User> {
        return runCatching {  authDataSource.registerUser(params)}
    }

    override suspend fun loginUser(params: UserLoginParams): BaseResponse<Any> {
        TODO("Not yet implemented")
    }
}