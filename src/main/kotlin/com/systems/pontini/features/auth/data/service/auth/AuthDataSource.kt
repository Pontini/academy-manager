package com.systems.pontini.features.auth.data.service.auth

import com.systems.pontini.features.auth.domain.model.User
import com.systems.pontini.features.auth.presentation.model.CreateUserRequest


interface AuthDataSource {
    suspend fun registerUser(params: CreateUserRequest): User
    suspend fun loginUser(email: String, password: String): User
    suspend fun findUserByEmail(email: String): User?
}