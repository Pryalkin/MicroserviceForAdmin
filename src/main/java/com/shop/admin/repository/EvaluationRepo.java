package com.shop.admin.repository;

import com.shop.admin.model.product.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepo extends JpaRepository<Evaluation, Long> {
}
