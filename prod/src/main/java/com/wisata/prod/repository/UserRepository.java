package com.wisata.prod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisata.prod.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}