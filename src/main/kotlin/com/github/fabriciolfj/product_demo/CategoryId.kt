package com.github.fabriciolfj.product_demo

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class CategoryId(
    @Column(name = "id")
    val id: String,

    @Column(name = "code")
    val code: String
) : Serializable {

    constructor() : this("", "")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CategoryId

        if (id != other.id) return false
        if (code != other.code) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + code.hashCode()
        return result
    }
}