package com.systems.pontini.config

import com.systems.pontini.core.Mapper
import com.systems.pontini.features.auth.data.datasource.mapper.ResultRowToUserMapper
import com.systems.pontini.features.auth.data.repository.AuthRepositoryImpl
import com.systems.pontini.features.auth.data.service.auth.AuthDataSource
import com.systems.pontini.features.auth.data.service.auth.AuthLocalDataSourceImpl
import com.systems.pontini.features.auth.domain.model.User
import com.systems.pontini.features.auth.domain.repository.AuthRepository
import com.systems.pontini.features.auth.domain.usecase.LoginUserUseCase
import com.systems.pontini.features.auth.domain.usecase.LoginUserUseCaseImpl
import com.systems.pontini.features.auth.domain.usecase.RegisterUserUseCase
import com.systems.pontini.features.auth.domain.usecase.RegisterUserUseCaseImpl
import com.systems.pontini.features.auth.presentation.AuthController
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.ResultRow
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDependencyInjection() {
    install(Koin) {
        slf4jLogger()
        modules(authModule)
    }

}

val authModule = module {
    single { AuthController(get(),get()) }
    single<RegisterUserUseCase> { RegisterUserUseCaseImpl(get(),) }
    single<LoginUserUseCase> { LoginUserUseCaseImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<AuthDataSource> { AuthLocalDataSourceImpl(get()) }
    single<ResultRowToUserMapper> { ResultRowToUserMapper() }

}