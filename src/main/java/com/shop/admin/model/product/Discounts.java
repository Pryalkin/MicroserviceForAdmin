package com.shop.admin.model.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Discounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String discountNumber;
    private Double discount;
    private LocalDate before;
    @NotNull
    @OneToMany(
            mappedBy = "discount",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product) {
        products.add(product);
        product.setDiscount(this);
    }

    public void addAllProducts(Set<Product> productAll) {
        products.addAll(productAll);
        productAll.forEach(product -> product.setDiscount(this));
    }
}
