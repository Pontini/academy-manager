package com.systems.pontini.features.auth.di

import com.systems.pontini.features.auth.data.datasource.mapper.ResultRowToUserMapper
import com.systems.pontini.features.auth.data.repository.AuthRepositoryImpl
import com.systems.pontini.features.auth.data.service.auth.AuthLocalDataSourceImpl
import com.systems.pontini.features.auth.domain.usecase.LoginUserUseCaseImpl
import com.systems.pontini.features.auth.domain.usecase.RegisterUserUseCaseImpl
import com.systems.pontini.features.auth.presentation.AuthController


object AuthProvider {
    fun provideAuthController(): AuthController = AuthController(
        registerUserUseCase = RegisterUserUseCaseImpl(
            authRepository = AuthRepositoryImpl(authDataSource =
            AuthLocalDataSourceImpl(resultRowToUserMapper = ResultRowToUserMapper()))
        ),
        loginUserUseCase = LoginUserUseCaseImpl(
            authRepository = AuthRepositoryImpl(
                AuthLocalDataSourceImpl(resultRowToUserMapper = ResultRowToUserMapper())
            )
        )
    )
}
