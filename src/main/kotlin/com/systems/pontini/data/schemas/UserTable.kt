package com.systems.pontini.data.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object UserTable : Table("users") {
    val id = integer("id").autoIncrement()
    val fullName = varchar("full_name", 256)
    val email = varchar("email", 256)
    val password = text("password").clientDefault { "123" }
    val cpf = text("cpf")
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }

    override val primaryKey = PrimaryKey(id)
}