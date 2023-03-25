package com.shop.admin.repository;

import com.shop.admin.model.user.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepo extends JpaRepository<Organization, Long> {

    Optional<List<Organization>> findByActivity(String activity);

    Optional<Organization> findByName(String name);
}
