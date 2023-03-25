package com.shop.admin.repository;

import com.shop.admin.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Set<Product>> findByRegistered(Boolean flag);
    Optional<Product> findByProductId(String productId);

}
