package com.lingua.market.persistence.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lingua.market.persistence.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long>{
    Optional<Seller> findByAuthUser(String authUser);
    Optional<Seller> findByDisplayName(String displayName);
}
