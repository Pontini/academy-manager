package com.systems.pontini.features.auth.data.repository

import com.systems.pontini.features.auth.data.service.auth.AuthDataSource
import com.systems.pontini.features.auth.domain.model.User
import com.systems.pontini.features.auth.domain.repository.AuthRepository
import com.systems.pontini.features.auth.presentation.model.CreateUserRequest
import com.systems.pontini.features.auth.presentation.model.UserLoginRequest


class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource,
) : AuthRepository {

    override suspend fun registerUser(params: CreateUserRequest): Result<User> {
        return runCatching { authDataSource.registerUser(params) }
    }

    override suspend fun loginUser(params: UserLoginRequest): Result<User> {
        return runCatching { authDataSource.loginUser(params.email, params.password) }
    }
}