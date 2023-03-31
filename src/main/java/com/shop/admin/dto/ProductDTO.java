package com.shop.admin.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ProductDTO {

    private String prodId;
    private String name;
    private String description;
    private String organization;
    private Double price;
    private Boolean registered;
    private Boolean isActive;
    private Set<String> keywords = new HashSet<>();
    private Set<CharacteristicDTO> characteristics = new HashSet<>();
}
