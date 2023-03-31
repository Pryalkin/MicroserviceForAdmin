package com.shop.admin.service;

import com.shop.admin.dto.DiscountDTO;

import java.util.Map;
import java.util.Set;

public interface DiscountsService {
    void create(DiscountDTO discountDTO);

    void add(Map<String, Set<String>> discountProducts);
}
