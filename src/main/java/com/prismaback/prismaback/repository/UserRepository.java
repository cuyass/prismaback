package com.prismaback.prismaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prismaback.prismaback.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
