package com.shop.admin.model.user;
import com.shop.admin.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String logo;
    @OneToMany(mappedBy = "organization")
    private Set<Product> products;
    private String activity;
    @ManyToOne
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private User user;

}
