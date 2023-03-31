package com.shop.admin.model;

import com.shop.admin.model.product.Serial;
import com.shop.admin.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @OneToMany(
            mappedBy = "purchase",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Serial> serials = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(name = "date_of_purchase")
    @EqualsAndHashCode.Include
    private LocalDateTime dateOfPurchase;

    public void addStock(Serial serial) {
        serials.add(serial);
        serial.setPurchase(this);
    }
}
