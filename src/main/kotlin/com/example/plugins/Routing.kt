package com.example.plugins

import com.example.features.login.configureLoginRouting
import com.example.features.signUp.configureSignUpRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    configureLoginRouting()
    configureSignUpRouting()
}
