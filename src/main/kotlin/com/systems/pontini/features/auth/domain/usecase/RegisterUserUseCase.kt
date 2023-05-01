package com.systems.pontini.features.auth.domain.usecase

import com.systems.pontini.features.auth.domain.model.User
import com.systems.pontini.features.auth.presentation.model.CreateUserRequest

interface RegisterUserUseCase {
    suspend operator fun invoke(payment: CreateUserRequest): Result<User?>
}