package ru.topbun.utills

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.topbun.model.error.ErrorDTO

data class AppException(val code: HttpStatusCode, override val message: String = "") : Exception()

object Error {
    const val UNAUTHORIZED = "Пользователь не авторизован"
    const val NOT_FOUND = "Пользователь с указанной почтой или паролем не найден"
    const val USER_NOT_FOUND = "Пользователь не найден"
    const val USER_EXISTS = "Пользователь с такой почтой уже зарегистрирован"
    const val CATEGORY_EXISTS = "Категория с таким именем уже существует"
    const val CATEGORY_NOT_FOUND = "Категория с таким id не найдена"
    const val INVALID_EMAIL = "Неверный формат электронной почты"
    const val USERNAME_LENGTH = "Длина псевдонима должна быть не менее 4 символов"
    const val PASSWORD_LENGTH = "Длина пароля должна быть не менее 6 символов"
    const val CATEGORY_LENGTH = "Длина названия категории должна быть не менее 5 символов"
    const val FORBIDDEN = "Отказано в доступе"
    const val BAD_REQUEST = "Произошла ошибка"
    const val PARAMS_INT = "В параметрах укажите число"
}

suspend fun RoutingCall.createError(code: HttpStatusCode, errorMessage: String = "") =
    respond(code, ErrorDTO(code.value, errorMessage))

suspend fun RoutingCall.wrapperException(catch: () -> Unit = {}, finally: () -> Unit = {}, block: suspend () -> Unit){
    try {
        block()
    } catch (e: AppException){
        catch()
        createError(code = e.code, errorMessage = e.message)
    } finally {
        finally()
    }
}