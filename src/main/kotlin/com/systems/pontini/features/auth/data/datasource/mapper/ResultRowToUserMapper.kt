package com.systems.pontini.features.auth.data.datasource.mapper

import com.systems.pontini.core.Mapper
import com.systems.pontini.features.auth.data.schemas.UserTable
import com.systems.pontini.features.auth.domain.model.User
import com.systems.pontini.core.security.JwtConfig
import org.jetbrains.exposed.sql.ResultRow


internal class ResultRowToUserMapper : Mapper<ResultRow, User> {
    override fun map(source: ResultRow): User {
        return source.run {
            val token = JwtConfig.instance.createAccessToken(this[UserTable.id])
            User(fullName = this[UserTable.fullName], email = this[UserTable.email], token = token)
        }
    }
}