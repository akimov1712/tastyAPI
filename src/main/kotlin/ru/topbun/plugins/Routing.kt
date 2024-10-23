package ru.topbun.plugins

import ru.topbun.features.login.configureLoginRouting
import ru.topbun.features.signUp.configureSignUpRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.topbun.features.categories.configureCategoryRouting

fun Application.configureRouting() {
    configureLoginRouting()
    configureSignUpRouting()
    configureCategoryRouting()
}
