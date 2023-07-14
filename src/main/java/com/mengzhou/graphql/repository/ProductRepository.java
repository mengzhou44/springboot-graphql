package com.mengzhou.graphql.repository;

import com.mengzhou.graphql.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
