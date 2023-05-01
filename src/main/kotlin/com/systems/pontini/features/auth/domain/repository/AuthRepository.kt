package com.systems.pontini.features.auth.domain.repository

import com.systems.pontini.features.auth.domain.model.User
import com.systems.pontini.features.auth.presentation.model.CreateUserRequest
import com.systems.pontini.features.auth.presentation.model.UserLoginRequest

interface AuthRepository {
    suspend fun registerUser(params: CreateUserRequest): Result<User>
    suspend fun loginUser(params: UserLoginRequest): Result<User>
}