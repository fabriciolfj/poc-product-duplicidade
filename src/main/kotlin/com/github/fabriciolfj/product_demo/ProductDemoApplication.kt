package com.github.fabriciolfj.product_demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductDemoApplication

fun main(args: Array<String>) {
	runApplication<ProductDemoApplication>(*args)
}
