package com.systems.pontini.features.auth.di

import com.systems.pontini.features.auth.data.datasource.mapper.ResultRowToUserMapper
import com.systems.pontini.features.auth.data.repository.AuthRepositoryImpl
import com.systems.pontini.features.auth.data.service.auth.AuthDataSource
import com.systems.pontini.features.auth.data.service.auth.AuthLocalDataSourceImpl
import com.systems.pontini.features.auth.domain.repository.AuthRepository
import com.systems.pontini.features.auth.domain.usecase.LoginUserUseCase
import com.systems.pontini.features.auth.domain.usecase.LoginUserUseCaseImpl
import com.systems.pontini.features.auth.domain.usecase.RegisterUserUseCase
import com.systems.pontini.features.auth.domain.usecase.RegisterUserUseCaseImpl
import com.systems.pontini.features.auth.presentation.AuthController
import org.koin.dsl.module

val authModule = module {
    single { AuthController(get(),get()) }
    single<RegisterUserUseCase> { RegisterUserUseCaseImpl(get(),) }
    single<LoginUserUseCase> { LoginUserUseCaseImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<AuthDataSource> { AuthLocalDataSourceImpl(get()) }
    single<ResultRowToUserMapper> { ResultRowToUserMapper() }

}