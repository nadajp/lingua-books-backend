package com.lingua.market.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lingua.market.persistence.dao.SellerRepository;
import com.lingua.market.persistence.model.Seller;
import com.lingua.market.web.dto.SellerDTO;
import com.lingua.market.web.exception.ResourceNotFoundException;

@Service
public class SellerService {
    
    private final ModelMapper modelMapper;

    private SellerRepository sellerRepository;

    public SellerService(ModelMapper modelMapper, SellerRepository sellerRepository) {
        this.modelMapper = modelMapper;
        this.sellerRepository = sellerRepository;
    }

    public List<SellerDTO> getAllSellers() {
        List<Seller> sellers = sellerRepository.findAll();
        return sellers.stream()
                .map(seller -> modelMapper.map(seller, SellerDTO.class))
                .collect(Collectors.toList());
    }

    public SellerDTO getSellerById(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found for this id :: " + sellerId));
        
        return modelMapper.map(seller, SellerDTO.class);
    }    

    public Seller createSeller(SellerDTO sellerDTO) {
        Seller seller = modelMapper.map(sellerDTO, Seller.class);
        Seller createdSeller = sellerRepository.save(seller);
        return createdSeller;
    }
}
