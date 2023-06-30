package com.lingua.market.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lingua.market.persistence.model.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long>{
    
}
