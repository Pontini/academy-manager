package com.systems.pontini.features.auth.data.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object RegisterClass : Table("register-class"){
    val id = UserTable.integer("user_id").autoIncrement()
    val createdAt = datetime("date_class").clientDefault { LocalDateTime.now() }
}