package com.systems.pontini.features.auth.data

import com.systems.pontini.features.auth.data.schemas.RegisterClass
import com.systems.pontini.features.auth.data.schemas.UserTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    private const val DRIVER_CLASS_NAME = "org.postgresql.Driver"
    private const val PASSWORD = "manager123"
    private const val USER_NAME = "manager"
    private const val PORT = 5432
    private const val JDB_URL = "jdbc:postgresql://manageracademy.c6t72zaqynvg.us-east-2.rds.amazonaws.com/bdmanageracademy"
    private const val IS_AUTO_COMMIT = false
    private const val MAXIMUM_POOL_SIZE = 3
    private const val TRANSACTION_ISOLATION = "TRANSACTION_REPEATABLE_READ"

    fun init() {
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(UserTable, RegisterClass)
        }

    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = DRIVER_CLASS_NAME
        config.password = PASSWORD
        config.username = USER_NAME
        config.addDataSourceProperty("port",PORT)
        config.jdbcUrl = JDB_URL
        config.maximumPoolSize = MAXIMUM_POOL_SIZE
        config.isAutoCommit = IS_AUTO_COMMIT
        config.transactionIsolation = TRANSACTION_ISOLATION
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T = withContext(Dispatchers.IO) {
        transaction {
            block()
        }
    }
}