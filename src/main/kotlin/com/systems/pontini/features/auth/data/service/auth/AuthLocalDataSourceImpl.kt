package com.systems.pontini.features.auth.data.service.auth

import com.systems.pontini.features.auth.data.DatabaseFactory.dbQuery
import com.systems.pontini.features.auth.data.datasource.mapper.ResultRowToUserMapper
import com.systems.pontini.features.auth.data.schemas.UserTable
import com.systems.pontini.features.auth.domain.excpetion.UnauthorizedException
import com.systems.pontini.features.auth.domain.excpetion.UserExistException
import com.systems.pontini.features.auth.domain.excpetion.UserNotRegisteredException
import com.systems.pontini.features.auth.domain.model.User
import com.systems.pontini.features.auth.presentation.model.CreateUserRequest
import com.systems.pontini.core.security.hash
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

private const val NOT_REGISTERED_USER = "Nao foi possível registrar o usuário"
private const val ALREADY_REGISTERED_USER = "Usuário já registrado"
private const val LOGIN_OR_PASSWORD_INVALID = "Login ou senha inválido"

internal class AuthLocalDataSourceImpl(private val resultRowToUserMapper: ResultRowToUserMapper) : AuthDataSource {

    override suspend fun registerUser(params: CreateUserRequest): User {
         try {
            if (isEmailExist(params.email)) {
                throw UserExistException(ALREADY_REGISTERED_USER)
            }
            var statement: InsertStatement<Number>? = null
            dbQuery {
                statement = UserTable.insert {
                    it[email] = params.email
                    it[password] = hash(params.password)
                    it[fullName] = params.fullName
                    it[cpf] = params.cpf
                }
            }
            statement?.resultedValues?.get(0)?.let {
                return resultRowToUserMapper.map(it)

            }
            throw UserNotRegisteredException(NOT_REGISTERED_USER)

        } catch (e: Exception) {
            throw UserNotRegisteredException(NOT_REGISTERED_USER)
        }
    }

    override suspend fun loginUser(email: String, password: String): User {
        val hashedPassword = hash(password)
        val userRow = dbQuery { UserTable.select { UserTable.email eq email and (UserTable.password eq hashedPassword) }.firstOrNull() }
        userRow?.let {
            return resultRowToUserMapper.map(userRow)
        }
        throw UnauthorizedException(LOGIN_OR_PASSWORD_INVALID)
    }

    override suspend fun findUserByEmail(email: String): User? {
        val user = dbQuery {
            UserTable.select { UserTable.email.eq(email) }
                .map { resultRowToUserMapper.map(it) }.singleOrNull()
        }
        return user
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return findUserByEmail(email) != null
    }
}