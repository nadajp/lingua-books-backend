package com.lingua.market.persistence.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lingua.market.persistence.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByAuthUserId(String authUserId);
        Optional<User> findByEmail(String email);
}
