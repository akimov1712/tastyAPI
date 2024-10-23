package ru.topbun.features.categories

import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.topbun.model.category.Categories
import ru.topbun.model.user.Users
import ru.topbun.utills.*

class CategoryController(
    private val call: RoutingCall
) {

    suspend fun addCategory(){
        call.wrapperException {
            val user = call.getUserFromToken()
            if (!user.isAdmin) throw AppException(HttpStatusCode.Forbidden, Error.FORBIDDEN)
            val categoryReceive = call.receive<CategoryReceive>()
            categoryReceive.isValid()
            Categories.insertCategory(categoryReceive.name, categoryReceive.image)
            Categories.fetchCategory(categoryReceive.name)?.let {
                call.respond(HttpStatusCode.OK, CategoryResponse(it))
            } ?: call.respond(HttpStatusCode.BadRequest, Error.BAD_REQUEST)
        }

    }

}