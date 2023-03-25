package com.shop.admin.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrganizationDto implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String username;
}
