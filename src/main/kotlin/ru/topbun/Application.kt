package ru.topbun

import ru.topbun.plugins.*
import ru.topbun.utills.generateToken
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