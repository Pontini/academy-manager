package com.systems.pontini.features.auth.domain.usecase

import com.systems.pontini.features.auth.data.service.auth.AuthDataSource
import com.systems.pontini.features.auth.domain.excpetion.UserRegisterInputInvalidException
import com.systems.pontini.features.auth.domain.model.User
import com.systems.pontini.features.auth.domain.repository.AuthRepository
import com.systems.pontini.features.auth.presentation.model.CreateUserRequest

class RegisterUserUseCaseImpl(private val authRepository: AuthRepository) : RegisterUserUseCase {
    override suspend fun invoke(createUserRequest: CreateUserRequest): Result<User> {
        if (createUserRequest.isValidateField().not()) {
            throw UserRegisterInputInvalidException("Email é necessário")
        }
        return  authRepository.registerUser(createUserRequest)
    }
}