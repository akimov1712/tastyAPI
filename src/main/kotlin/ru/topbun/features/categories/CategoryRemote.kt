package ru.topbun.features.categories

import io.ktor.http.*
import kotlinx.serialization.Serializable
import ru.topbun.model.category.CategoryDTO
import ru.topbun.utills.AppException
import ru.topbun.utills.Error

@Serializable
data class AddCategoryReceive(
    val name: String,
    val image: String,
){

    fun isValid() = when{
        name.length < 5 -> throw AppException(HttpStatusCode.BadRequest, Error.CATEGORY_LENGTH)
        else -> null
    }

}

@Serializable
data class AddCategoryResponse(
    val category: CategoryDTO,
)

@Serializable
data class GetAllCategoryResponse(
    val categories: List<CategoryDTO>,
)

