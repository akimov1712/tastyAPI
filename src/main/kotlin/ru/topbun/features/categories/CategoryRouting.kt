package ru.topbun.features.categories

import ru.topbun.features.categories.CategoryController
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureCategoryRouting(){
    routing {
        authenticate {
            post("/category") {
                val categoryController = CategoryController(call)
                categoryController.addCategory()
            }
        }

    }
}