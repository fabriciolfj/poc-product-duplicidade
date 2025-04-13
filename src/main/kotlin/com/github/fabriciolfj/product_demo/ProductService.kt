package com.github.fabriciolfj.product_demo

import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional


@Service
class ProductService(private val productRepository: ProductRepository,
                     private val categoryRepository: CategoryRepository) {

    @Transactional(propagation = Propagation.REQUIRED)
    fun saveAllProducts(products: List<Product>): List<Product> {
        products.forEach { product ->
            product.categories =  updateCategories(product).toMutableList()
        }

        return productRepository.saveAll(products)
    }

    private fun updateCategories(product: Product) : List<Category> {
        return product.categories.map { category ->
            val result = categoryRepository.findById(category.categoryId!!)

            if (result.isPresent) {
                BeanUtils.copyProperties(category, result.get(), "id", "code")
                result.get()
            } else {
                category
            }
        }
    }


    fun findAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    fun findProductsByCategoryCode(categoryCode: String): List<Product> {
        return productRepository.findByCategoriesCategoryIdCode(categoryCode)
    }
}