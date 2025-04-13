package com.github.fabriciolfj.product_demo

import jakarta.persistence.*

@Entity
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    var categories: MutableList<Category> = mutableListOf()) {

    fun addCategory(category: Category) {
        categories.add(category)
    }
}