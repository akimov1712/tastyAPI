package ru.topbun.features.recipe

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureRecipeRouting(){
    routing {
        get("/recipe") {
            val controller = RecipeController(call)
            controller.getRecipeList()
        }
        get("/recipe/{id}") {
            val controller = RecipeController(call)
            controller.getRecipeWithId()
        }
        authenticate {
            get("/recipe/my") {
                val controller = RecipeController(call)
                controller.getMyRecipeList()
            }
            post("/recipe") {
                val controller = RecipeController(call)
                controller.addRecipe()
            }
        }
    }
}