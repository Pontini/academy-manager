package com.systems.pontini.domain.usecase

import com.systems.pontini.domain.model.User
import com.systems.pontini.routes.CreateUserRequest

interface RegisterUserUseCase {
    suspend operator fun invoke(payment: CreateUserRequest): Result<User?>
}