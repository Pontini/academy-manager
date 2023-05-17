package com.systems.pontini.features.auth.domain.usecase

import com.systems.pontini.features.auth.domain.excpetion.UserRegisterInputInvalidException
import com.systems.pontini.features.auth.domain.model.User
import com.systems.pontini.features.auth.domain.repository.AuthRepository
import com.systems.pontini.features.auth.presentation.model.UserLoginRequest

private const val LOGIN_FIELD_REQUIRED = "OS campos login e senha sao necessários"

class LoginUserUseCaseImpl(private val authRepository: AuthRepository) : LoginUserUseCase {
    override suspend fun invoke(createUserRequest: UserLoginRequest): Result<User> {

        if (createUserRequest.validateField()) {
            return authRepository.loginUser(createUserRequest)
        }
        throw UserRegisterInputInvalidException(LOGIN_FIELD_REQUIRED)
    }
}