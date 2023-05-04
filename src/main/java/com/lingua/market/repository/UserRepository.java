package com.lingua.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lingua.market.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
