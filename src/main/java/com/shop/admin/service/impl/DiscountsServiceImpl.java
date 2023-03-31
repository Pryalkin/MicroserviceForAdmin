package com.shop.admin.service.impl;

import com.shop.admin.dto.DiscountDTO;
import com.shop.admin.model.product.Discounts;
import com.shop.admin.model.product.Product;
import com.shop.admin.repository.DiscountsRepo;
import com.shop.admin.repository.ProductRepo;
import com.shop.admin.service.DiscountsService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DiscountsServiceImpl implements DiscountsService {

    private final DiscountsRepo discountsRepo;
    private final ProductRepo productRepo;

    @Override
    public void create(DiscountDTO discountDTO) {
        Discounts discounts = new Discounts();
        String discountNumber = generateDiscountNumber();
        while (discountsRepo.findByDiscountNumber(discountNumber).isPresent())
            discountNumber = generateDiscountNumber();
        discounts.setDiscountNumber(discountNumber);
        discounts.setDiscount(discountDTO.getDiscount());
        discounts.setBefore(discountDTO.getBefore());
        Set<Product> products = discountDTO.getProductId().stream()
                .map(productId -> productRepo.findByProdId(productId).get()
        ).collect(Collectors.toSet());
        discounts.addAllProducts(products);
        discountsRepo.save(discounts);
    }

    @Override
    public void add(Map<String, Set<String>> discountProducts) {
        discountProducts.entrySet().forEach(s ->
                s.getValue().forEach(v -> {
                    Discounts discounts = discountsRepo.findByDiscountNumber(s.getKey()).get();
                    Product product = productRepo.findByProdId(v).get();
                    discounts.addProduct(product);
                    discountsRepo.save(discounts);
                }));
    }

    private String generateDiscountNumber() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}
