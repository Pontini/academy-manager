package com.systems.pontini.domain.repository

import com.systems.pontini.base.BaseResponse
import com.systems.pontini.domain.model.User
import com.systems.pontini.routes.CreateUserRequest
import com.systems.pontini.routes.UserLoginParams

interface AuthRepository {
    suspend fun registerUser(params: CreateUserRequest): Result<User>
    suspend fun loginUser(params: UserLoginParams): BaseResponse<Any>
}