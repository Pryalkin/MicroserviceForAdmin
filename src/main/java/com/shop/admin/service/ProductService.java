package com.shop.admin.service;

import com.shop.admin.dto.ProductDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {
    Set<ProductDTO> findUnregisteredProducts();

    void registration(Map<Boolean, List<String>> products);
}
