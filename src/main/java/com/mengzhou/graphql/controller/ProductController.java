package com.mengzhou.graphql.controller;

import com.mengzhou.graphql.model.Product;
import com.mengzhou.graphql.repository.ProductRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private ProductRepository productRepository;

	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@QueryMapping
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}
}