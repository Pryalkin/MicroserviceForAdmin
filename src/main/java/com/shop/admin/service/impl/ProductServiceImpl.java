package com.shop.admin.service.impl;

import com.shop.admin.enumeration.Activity;
import com.shop.admin.enumeration.Role;
import com.shop.admin.model.product.Product;
import com.shop.admin.model.user.Organization;
import com.shop.admin.model.user.User;
import com.shop.admin.repository.ProductRepo;
import com.shop.admin.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public Set<Product> findUnregisteredProducts() {
        return productRepo.findByRegistered(FALSE).orElse(new HashSet<>());
    }

    @Override
    public void registration(Map<Boolean, List<String>> products) {
        products.keySet().forEach(key -> {
            if (key){
                products.get(TRUE).forEach(productId -> {
                    Product product = productRepo.findByProductId(productId).get();
                    product.setRegistered(TRUE);
                    productRepo.save(product);
                });
            } else {
                products.get(FALSE).forEach(productId -> {
                    Product product = productRepo.findByProductId(productId).get();
                    productRepo.delete(product);
                });
            }
        });
    }
}
