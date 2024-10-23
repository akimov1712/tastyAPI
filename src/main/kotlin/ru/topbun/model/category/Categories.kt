package ru.topbun.model.category

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Categories : Table("Categories") {
    private val id = integer("id").autoIncrement()
    private val name = varchar("name", 24)
    private val image = varchar("image", 50)

    override val primaryKey = PrimaryKey(id)

    fun fetchCategory(name: String): CategoryDTO? =
        transaction { selectAll().where { Categories.name eq name }.firstOrNull()?.toCategory() }

    fun fetchCategory(id: Int): CategoryDTO? =
        transaction { selectAll().where { Categories.id eq id }.firstOrNull()?.toCategory() }

    fun containsCategory(name: String): Boolean = fetchCategory(name) != null

    fun insertCategory(name: String, image: String) {
        transaction { insert {
            it[Categories.name] = name
            it[Categories.image] = image
        } }
    }

    fun selectAllCategories() = transaction { selectAll().map{ it.toCategory() }}

    private fun ResultRow.toCategory(): CategoryDTO {
        return CategoryDTO(
            id = this[id],
            name = this[name],
            image = this[image],
        )
    }

}