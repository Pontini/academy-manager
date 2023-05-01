package com.systems.pontini.features.auth.presentation.model

data class CreateUserRequest(
    val fullName: String,
    val email: String,
    val password: String,
    val cpf:String,
) {
    fun isValidateField(): Boolean {
        return fullName.isNullOrEmpty().not() && email.isNullOrEmpty().not() && password.isNullOrEmpty().not()
                && cpf.isNullOrEmpty().not()
    }
}
