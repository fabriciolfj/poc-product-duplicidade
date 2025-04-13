package com.github.fabriciolfj.product_demo

import java.math.BigDecimal

data class CategoryDTO(
    val id: String,
    val code: String,
    val summary: BigDecimal
)