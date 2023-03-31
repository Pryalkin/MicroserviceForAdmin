package com.shop.admin.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shop.admin.json.deserializer.NotificationSetDeserializer;
import com.shop.admin.json.deserializer.OrganizationSetDeserializer;
import com.shop.admin.json.deserializer.PurchaseSetDeserializer;
import com.shop.admin.json.serializer.NotificationSetSerializer;
import com.shop.admin.json.serializer.OrganizationSetSerializer;
import com.shop.admin.json.serializer.PurchaseSetSerializer;
import com.shop.admin.model.Notification;
import com.shop.admin.model.Purchase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @NotNull
    @EqualsAndHashCode.Include
    private String username;
    @Email
    @NotNull
    @EqualsAndHashCode.Include
    private String email;
    @EqualsAndHashCode.Include
    private String password;
    private Double balance;
    @JsonDeserialize(using = OrganizationSetDeserializer.class)
    @JsonSerialize(using = OrganizationSetSerializer.class)
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Organization> organizations = new HashSet<>();
    @JsonDeserialize(using = NotificationSetDeserializer.class)
    @JsonSerialize(using = NotificationSetSerializer.class)
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Notification> notifications = new HashSet<>();
    private String role;
    private String[] authorities;
    private String activity;
    @JsonDeserialize(using = PurchaseSetDeserializer.class)
    @JsonSerialize(using = PurchaseSetSerializer.class)
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Purchase> purchases = new HashSet<>();

    public void addOrganization(Organization organization) {
        organizations.add(organization);
        organization.setUser(this);
    }

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
        purchase.setUser(this);
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
        notification.setUser(this);
    }
}
