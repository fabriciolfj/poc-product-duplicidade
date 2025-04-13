package com.github.fabriciolfj.product_demo

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

    @PostMapping("/batch")
    fun createProducts(@RequestBody productDTOs: List<ProductDTO>): ResponseEntity<List<ProductDTO>> {
        val products = productDTOs.map { productDTO ->
            val product = Product(
                name = productDTO.name
            )

            productDTO.categories.forEach { categoryDTO ->
                val category = Category(categoryId = CategoryId(categoryDTO.id, categoryDTO.code), summary = categoryDTO.summary)
                product.addCategory(category)
            }

            product
        }


        val savedProducts = productService.saveAllProducts(products)

        val savedProductDTOs = savedProducts.map { savedProduct ->
            ProductDTO(
                id = savedProduct.id,
                name = savedProduct.name,
                categories = savedProduct.categories.map { category ->
                    CategoryDTO(
                        id = category.categoryId!!.id,
                        code = category.categoryId?.code ?: "",
                        summary = category.summary
                    )
                }
            )
        }

        return ResponseEntity(savedProductDTOs, HttpStatus.CREATED)
    }

    @GetMapping("/by-category/{categoryCode}")
    fun getProductsByCategoryCode(@PathVariable categoryCode: String): ResponseEntity<List<ProductDTO>> {
        val products = productService.findProductsByCategoryCode(categoryCode)

        val productDTOs = products.map { product ->
            ProductDTO(
                id = product.id,
                name = product.name,
                categories = product.categories.map { category ->
                    CategoryDTO(
                        id = category.categoryId!!.id,
                        code = category.categoryId?.code ?: "",
                        summary = category.summary
                    )
                }
            )
        }

        return ResponseEntity(productDTOs, HttpStatus.OK)
    }

    @GetMapping("/products")
    fun getAllProducts(): ResponseEntity<List<ProductDTO>> {
        val products = productService.findAllProducts()

        val productDTOs = products.map { product ->
            ProductDTO(
                id = product.id,
                name = product.name,
                categories = product.categories.map { category ->
                    CategoryDTO(
                        id = category.categoryId!!.id,
                        code = category.categoryId?.code ?: "",
                        summary = category.summary
                    )
                }
            )
        }

        return ResponseEntity(productDTOs, HttpStatus.OK)
    }
}