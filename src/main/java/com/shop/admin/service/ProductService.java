package com.shop.admin.service;

import com.shop.admin.model.product.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {
    Set<Product> findUnregisteredProducts();

    void registration(Map<Boolean, List<String>> products);
}
