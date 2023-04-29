package com.systems.pontini.routes

data class CreateUserRequest(
    val fullName: String,
    val email: String,
    val password: String,
    val cpf:String,
    val avatar: String
)
