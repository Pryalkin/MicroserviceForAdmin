package com.shop.admin.repository;

import com.shop.admin.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Long> {
    Optional<Set<Purchase>> findByUserUsername(String username);

}
