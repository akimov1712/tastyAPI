package com.example.features.signUp

import com.example.features.login.LoginReceive
import com.example.features.login.LoginResponse
import com.example.model.user.Users
import com.example.utills.Error
import com.example.utills.generateToken
import com.example.utills.passwordVerify
import com.example.utills.toPasswordHash
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.math.sign

class SignUpController(
    private val call: RoutingCall
) {

    suspend fun signUp(){
        val signUp = call.receive<SignUpReceive>()
        val userIsFound = Users.containsUser(signUp.email)
        if (userIsFound) call.respond(HttpStatusCode.NotFound, Error.USER_FOUND)
        val passwordHash = signUp.password.toPasswordHash()
        Users.insertUser(
            username = signUp.username,
            email = signUp.email,
            password = passwordHash
        )
        call.respond(SignUpResponse(token = generateToken(signUp.email)))
    }

}