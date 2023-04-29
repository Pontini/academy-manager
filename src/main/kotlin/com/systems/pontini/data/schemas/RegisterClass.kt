package com.systems.pontini.data.schemas

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object RegisterClass : Table("register-class"){
    val id = UserTable.integer("id").autoIncrement()
    val createdAt = datetime("date_class").clientDefault { LocalDateTime.now() }
}