package com.systems.pontini.features.auth.presentation.model

data class UserLoginRequest(
    val email: String,
    val password: String
) {
    fun validateField(): Boolean {
        return email.isNullOrEmpty().not() && password.isNullOrEmpty().not()
    }
}