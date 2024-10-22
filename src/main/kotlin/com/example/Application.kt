package com.example

import com.example.plugins.*
import com.example.utills.generateToken
import io.ktor.server.application.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureDatabases()
    configureSecurity()
    configureRouting()
}