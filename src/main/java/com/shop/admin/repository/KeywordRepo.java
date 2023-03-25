package com.shop.admin.repository;

import com.shop.admin.model.product.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepo extends JpaRepository<Keyword, Long> {
}
