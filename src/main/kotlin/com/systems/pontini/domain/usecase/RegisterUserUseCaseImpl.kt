package com.systems.pontini.domain.usecase

import com.systems.pontini.domain.excpetion.UserRegisterInputInvalidException
import com.systems.pontini.domain.model.User
import com.systems.pontini.domain.repository.AuthRepository
import com.systems.pontini.routes.CreateUserRequest

class RegisterUserUseCaseImpl(private val authRepository: AuthRepository) : RegisterUserUseCase {
    override suspend fun invoke(createUserRequest: CreateUserRequest): Result<User> {

        if (createUserRequest.email.isNullOrEmpty()) {
            throw UserRegisterInputInvalidException("Email é necessário")
        }
        return authRepository.registerUser(createUserRequest)
    }
}