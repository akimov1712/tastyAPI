package ru.topbun.model.favorite

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Expression
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import ru.topbun.model.recipe.Recipes

object Favorites: IntIdTable("favorites") {

    val userId = integer("user_id")
    val recipeId = integer("recipe_id")

    fun insertFavorite(userId: Int, recipeId: Int) = transaction {
        insert {
            it[this.userId] = userId
            it[this.recipeId] = recipeId
        }
    }

    fun getRecipesFromUserId(userId: Int) = transaction {
        Recipes.select(Favorites.recipeId).where { Favorites.userId eq userId }
    }

}