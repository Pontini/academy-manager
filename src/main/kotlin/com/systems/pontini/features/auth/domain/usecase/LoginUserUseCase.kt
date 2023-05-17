package com.systems.pontini.features.auth.domain.usecase

import com.systems.pontini.features.auth.domain.model.User
import com.systems.pontini.features.auth.presentation.model.UserLoginRequest

interface LoginUserUseCase {
    suspend operator fun invoke(payment: UserLoginRequest): Result<User?>
}