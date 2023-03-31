package com.shop.admin.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class DiscountDTO {

    private Double discount;
    private LocalDate before;
    private Set<String> productId;

}
