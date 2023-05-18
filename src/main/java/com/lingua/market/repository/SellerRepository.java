package com.lingua.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lingua.market.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long>{
    
}
