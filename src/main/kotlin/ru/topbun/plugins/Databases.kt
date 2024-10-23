package ru.topbun.plugins

import at.favre.lib.crypto.bcrypt.BCrypt
import ru.topbun.model.category.Categories
import ru.topbun.model.user.Users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    Database.connect(
        url = environment.config.property("database.url").getString(),
        user = environment.config.property("database.user").getString(),
        driver = environment.config.property("database.driver").getString(),
        password = environment.config.property("database.password").getString(),
    )
    transaction { SchemaUtils.create(Users, Categories) }

}
