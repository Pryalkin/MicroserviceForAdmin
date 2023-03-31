package com.shop.admin.repository;

import com.shop.admin.model.product.Discounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountsRepo extends JpaRepository<Discounts, Long> {
    Optional<Discounts> findByDiscountNumber(String discountNumber);

}
