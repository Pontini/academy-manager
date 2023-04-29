package com.systems.pontini.data.db.extensions

import com.systems.pontini.data.schemas.UserTable
import com.systems.pontini.data.models.UserModel
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow?.toUser(): UserModel? {
    return if (this == null) null
    else UserModel(
        id = this[UserTable.id],
        fullName = this[UserTable.fullName],
        email = this[UserTable.email],
        createdAt = this[UserTable.createdAt].toString(),)
}