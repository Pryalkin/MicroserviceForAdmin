package com.shop.admin.repository;

import com.shop.admin.model.user.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface OrganizationRepo extends JpaRepository<Organization, Long> {

    Optional<Set<Organization>> findByActivity(String activity);

    Optional<Organization> findByName(String name);
}
