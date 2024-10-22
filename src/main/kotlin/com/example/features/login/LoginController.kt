package com.example.features.login

import com.example.model.user.Users
import com.example.utills.Error
import com.example.utills.generateToken
import com.example.utills.passwordVerify
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class LoginController(
    private val call: RoutingCall
) {

    suspend fun login(){
        val login = call.receive<LoginReceive>()
        val user = Users.fetchUser(login.email)
        user?.let {
            val passwordIsValid = passwordVerify(login.password, user.password)
            if (!passwordIsValid) call.respond(HttpStatusCode.NotFound, Error.NOT_FOUND)
            call.respond(LoginResponse(token = generateToken(login.email)))
        } ?: call.respond(HttpStatusCode.NotFound, Error.NOT_FOUND)
    }

}