package com.github.fabriciolfj.product_demo

data class ProductDTO(
    val id: Long? = null,
    val name: String,
    val categories: List<CategoryDTO> = emptyList()
)