package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.utills.Const
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.h2.engine.User
import java.util.*

fun Application.configureSecurity() {
    authentication {
        jwt {
            realm = Const.Jwt.REALM
            verifier(
                JWT.require(Algorithm.HMAC256(Const.Jwt.SECRET))
                    .withAudience(Const.Jwt.AUDIENCE)
                    .withIssuer(Const.Jwt.ISSUER)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(Const.Jwt.AUDIENCE)) {
                    JWTPrincipal(credential.payload)
                } else null
            }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Token недействителен или истек")
            }
        }
    }
}