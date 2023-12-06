package com.lingua.market.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lingua.market.persistence.dao.SellerRepository;
import com.lingua.market.persistence.dao.UserRepository;
import com.lingua.market.persistence.model.Seller;
import com.lingua.market.persistence.model.StripeStatus;
import com.lingua.market.persistence.model.User;
import com.lingua.market.web.dto.SellerDTO;
import com.lingua.market.web.exception.ResourceNotFoundException;
import com.lingua.market.web.exception.SellerAlreadyExistsException;

@Service
public class SellerService {
    
    private final ModelMapper modelMapper;

    private SellerRepository sellerRepository;

    private UserRepository userRepository;

    public SellerService(ModelMapper modelMapper, SellerRepository sellerRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
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

     public SellerDTO getSellerByAuthUser(String authUser) {
        Seller seller = sellerRepository.findByAuthUser(authUser)
            .orElseThrow(() -> new ResourceNotFoundException("Seller not found for this authId :: " + authUser));
        
        return modelMapper.map(seller, SellerDTO.class);
    }    

    public Seller createSeller(SellerDTO sellerDTO) throws SellerAlreadyExistsException {
        Optional<Seller> existingSellerByAuthUser = sellerRepository.findByAuthUser(sellerDTO.getAuthUser());
    
        // If the display name exists and is associated with a different authUser, throw an exception
        // if (existingSellerByDisplayName.isPresent() 
        //     && !existingSellerByDisplayName.get().getAuthUser().equals(sellerDTO.getAuthUser())) {
        //     throw new SellerAlreadyExistsException("Display name already in use");
        // }

        StripeStatus stripeStatus;
        try {
            System.out.println(null == sellerDTO.getStripeStatus() ? "null" : sellerDTO.getStripeStatus());
            stripeStatus = StripeStatus.valueOf(sellerDTO.getStripeStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            // Handle the case where the string does not match any enum constant
            // This might involve throwing a custom exception or handling it some other way
            throw new IllegalArgumentException("Invalid stripe status: " + sellerDTO.getStripeStatus());
        }
    
        // If a seller with the given authUserId exists, update the fields
        if (existingSellerByAuthUser.isPresent()) {
            Seller existingSeller = existingSellerByAuthUser.get();
            existingSeller.setCity(sellerDTO.getCity());
            existingSeller.setState(sellerDTO.getState());
            existingSeller.setCountry(sellerDTO.getCountry());
            existingSeller.setStripeAccountId(sellerDTO.getStripeAccountId());
            existingSeller.setStripeStatus(stripeStatus);
            return sellerRepository.save(existingSeller);
        }
    
        // If not, create a new seller
        Seller seller = new Seller();
        User user = userRepository.findByAuthUserId(sellerDTO.getAuthUser()).get();
        seller.setUser(user);
        seller.setAuthUser(sellerDTO.getAuthUser());
        seller.setCity(sellerDTO.getCity());
        seller.setState(sellerDTO.getState());
        seller.setCountry(sellerDTO.getCountry());
        seller.setStripeAccountId(sellerDTO.getStripeAccountId());
        seller.setStripeStatus(stripeStatus);
        return sellerRepository.save(seller);
    }
    

    public Seller updateSellerInfo(String authUser, SellerDTO sellerInfo) {
        Seller seller = sellerRepository.findByAuthUser(authUser)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found for this authUser"));
        
        Optional.ofNullable(sellerInfo.getCity()).ifPresent(seller::setCity);
        Optional.ofNullable(sellerInfo.getState()).ifPresent(seller::setState);
        Optional.ofNullable(sellerInfo.getCountry()).ifPresent(seller::setCountry);
        Optional.ofNullable(sellerInfo.getPostalCode()).ifPresent(seller::setPostalCode);
        if (sellerInfo.getStripeStatus() != null) {
            seller.setStripeStatus(StripeStatus.valueOf(sellerInfo.getStripeStatus().toUpperCase()));
        }
        return sellerRepository.save(seller);
    }
}
