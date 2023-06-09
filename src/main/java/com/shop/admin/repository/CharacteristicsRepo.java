package com.shop.admin.repository;

import com.shop.admin.model.product.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacteristicsRepo extends JpaRepository<Characteristic, Long> {
}
