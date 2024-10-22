package com.example.features.signUp

import kotlinx.serialization.Serializable

@Serializable
data class SignUpReceive(
    val email: String,
    val username: String,
    val password: String,
)

@Serializable
data class SignUpResponse(
    val token: String
)