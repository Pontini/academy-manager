package com.systems.pontini.features.auth.domain.model


data class User (
    val fullName:String,
    val email:String,
    val token:String
)