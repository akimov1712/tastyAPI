package ru.topbun.features.login

import ru.topbun.model.user.Users
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.topbun.utills.*

class LoginController(
    private val call: RoutingCall
) {

    suspend fun login(){
        call.wrapperException {
            val login = call.receive<LoginReceive>()
            val user = Users.getUserOrThrow(login.email)
            val passwordIsValid = passwordVerify(login.password, user.password)
            if (!passwordIsValid) throw AppException(HttpStatusCode.NotFound, Error.NOT_FOUND)
            call.respond(LoginResponse(token = generateToken(login.email)))
        }
    }

}