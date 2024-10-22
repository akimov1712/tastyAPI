package com.example.model.user

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Users: IntIdTable() {
    val username = text("username")
    val email = text("email").uniqueIndex()
    val password = text("password")

    fun fetchUser(email: String): UserDTO? =
        transaction { selectAll().where { Users.email eq email }.firstOrNull()?.toUser() }

    fun containsUser(email: String): Boolean = fetchUser(email) != null

    fun insertUser(username: String, email: String, password: String){
        transaction { insert {
            it[Users.username] = username
            it[Users.email] = email
            it[Users.password] = password
        } }
    }

    private fun ResultRow.toUser(): UserDTO {
        return UserDTO(
            username = this[username],
            email = this[email],
            password = this[password],
        )
    }

}