package com.systems.pontini.core.security

import io.ktor.server.auth.Principal

data class UserIdPrincipalForUser(val id: Int): Principal