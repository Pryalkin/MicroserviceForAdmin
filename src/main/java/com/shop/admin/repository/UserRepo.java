package com.shop.admin.repository;

import com.shop.admin.model.user.Organization;
import com.shop.admin.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "select * from users join organization on organization where users.organization = ?")
    User findOrganization(Organization org);



}
