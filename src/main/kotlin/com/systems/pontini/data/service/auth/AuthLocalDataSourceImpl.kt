package com.systems.pontini.data.service.auth

import com.systems.pontini.data.DatabaseFactory.dbQuery
import com.systems.pontini.data.datasource.mapper.ResultRowToUserMapper
import com.systems.pontini.data.db.extensions.toUser
import com.systems.pontini.data.models.UserModel
import com.systems.pontini.data.schemas.UserTable
import com.systems.pontini.domain.excpetion.UserExistException
import com.systems.pontini.domain.excpetion.UserNotRegisteredException
import com.systems.pontini.domain.model.User
import com.systems.pontini.routes.CreateUserRequest
import com.systems.pontini.security.hash
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

internal class AuthLocalDataSourceImpl(private val resultRowToUserMapper: ResultRowToUserMapper) : AuthDataSource {
    /* override suspend fun registerUser(params: CreateUserParams): UserModel? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = UserTable.insert {
                it[email] = params.email
                it[password] = hash(params.password)
                it[fullName] = params.fullName
                it[cpf] = params.cpf
            }
        }
        return statement?.resultedValues?.get(0).toUser()
    }

    override suspend fun loginUser(email: String, password: String): UserModel? {
        val hashedPassword = hash(password)
        val userRow = dbQuery { UserTable.select { UserTable.email eq email and (UserTable.password eq hashedPassword) }.firstOrNull() }
        return userRow.toUser()
    }

    override suspend fun findUserByEmail(email: String): UserModel? {
        val user = dbQuery {
            UserTable.select { UserTable.email.eq(email) }
                .map { it.toUser() }.singleOrNull()
        }
        return user
    }*/
    override suspend fun registerUser(params: CreateUserRequest): User {
        return try {
            if (isEmailExist(params.email)) {
                throw UserExistException("Usuário já registrado")
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

            throw UserNotRegisteredException("Nao foi possível registrar o usuário")

        }catch (e:Exception){
            throw UserNotRegisteredException("Nao foi possível registrar o usuário")
        }
    }

    override suspend fun loginUser(email: String, password: String): UserModel? {
        return UserModel(id = 0, fullName = "", email = "", authToken = null, createdAt = "")
    }

    override suspend fun findUserByEmail(email: String): UserModel? {
        val user = dbQuery {
            UserTable.select { UserTable.email.eq(email) }
                .map { it.toUser() }.singleOrNull()
        }
        return user
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return findUserByEmail(email) != null
    }
}