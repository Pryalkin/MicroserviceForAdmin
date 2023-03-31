package com.shop.admin.repository;

import com.shop.admin.model.product.Serial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerialRepo extends JpaRepository<Serial, Long> {
}
