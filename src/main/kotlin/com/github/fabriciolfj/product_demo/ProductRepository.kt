package com.github.fabriciolfj.product_demo

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String> {
    fun findByNameContaining(name: String): List<Product>
    fun findByCategoriesCategoryIdCode(categoryCode: String): List<Product>
}