package com.github.fabriciolfj.product_demo

import jakarta.persistence.*
import java.math.BigDecimal
import java.util.UUID


@Entity
@Table(name = "categories")
data class Category(
    @EmbeddedId
    var categoryId: CategoryId? = null,

    var summary: BigDecimal) {

    fun setId(id: String?) {
        if (id != null) {
            if (categoryId == null) {
                categoryId = CategoryId(id, UUID.randomUUID().toString())
            } else {
                categoryId = CategoryId(id, categoryId!!.code)
            }
        }
    }
}