package com.systems.pontini.di

import com.systems.pontini.data.datasource.mapper.ResultRowToUserMapper
import com.systems.pontini.data.repository.AuthRepositoryImpl
import com.systems.pontini.data.service.auth.AuthLocalDataSourceImpl
import com.systems.pontini.domain.usecase.RegisterUserUseCaseImpl
import com.systems.pontini.routes.auth.AuthController


object RepositoryProvider {
    fun provideAuthController(): AuthController = AuthController(
        RegisterUserUseCaseImpl(
            AuthRepositoryImpl(
                AuthLocalDataSourceImpl(
                    resultRowToUserMapper = ResultRowToUserMapper()
                )
            )
        )
    )
}