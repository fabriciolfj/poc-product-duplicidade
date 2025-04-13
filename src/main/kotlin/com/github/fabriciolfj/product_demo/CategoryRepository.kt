package com.github.fabriciolfj.product_demo

import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, CategoryId>