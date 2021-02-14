package com.grubneac.JwtApiDemo.repository;

import com.grubneac.JwtApiDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String name);
}
