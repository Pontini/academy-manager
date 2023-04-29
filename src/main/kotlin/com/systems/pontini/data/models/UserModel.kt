package com.systems.pontini.data.models

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserModel(
    val id: Int,
    val fullName: String,
    val email: String,
    var authToken: String? = null,
    val createdAt: String
)