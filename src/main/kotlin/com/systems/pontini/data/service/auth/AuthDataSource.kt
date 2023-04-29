package com.systems.pontini.data.service.auth

import com.systems.pontini.data.models.UserModel
import com.systems.pontini.domain.model.User
import com.systems.pontini.routes.CreateUserRequest


interface AuthDataSource {
    suspend fun registerUser(params: CreateUserRequest): User
    suspend fun loginUser(email: String, password: String): UserModel?
    suspend fun findUserByEmail(email: String): UserModel?
}