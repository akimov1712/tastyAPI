package com.example.utills

import at.favre.lib.crypto.bcrypt.BCrypt
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.model.user.Users.email
import io.ktor.server.auth.jwt.*

fun passwordVerify(receivePassword: String, hashPassword: String) = BCrypt.verifyer().verify(receivePassword.toCharArray(), hashPassword).verified
fun String.toPasswordHash() = BCrypt.withDefaults().hashToString(12, this.toCharArray())

fun generateToken(email: String): String{
    return JWT.create()
        .withAudience(Const.Jwt.AUDIENCE)
        .withIssuer(Const.Jwt.ISSUER)
        .withClaim(Const.Jwt.KEY_EMAIL, email)
        .sign(Algorithm.HMAC256(Const.Jwt.SECRET))
}

fun JWTPrincipal?.getUsername() = this?.payload?.getClaim(Const.Jwt.KEY_EMAIL)?.asString()

