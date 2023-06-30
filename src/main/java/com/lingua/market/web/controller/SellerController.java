package com.lingua.market.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lingua.market.service.SellerService;
import com.lingua.market.web.dto.SellerDTO;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping
    public ResponseEntity<List<SellerDTO>> getAllSellers() {
        return ResponseEntity.ok().body(sellerService.getAllSellers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerDTO> getSellerById(@PathVariable(value = "id") Long sellerId) {
        return ResponseEntity.ok().body(sellerService.getSellerById(sellerId));
    }

    @PostMapping(consumes = {"application/json"}) 
    public ResponseEntity<SellerDTO> createSeller(@Validated @RequestBody SellerDTO sellerDTO) {
        SellerDTO createdSellerDTO = sellerService.createSeller(sellerDTO);
        return ResponseEntity.created(URI.create("/sellers/" + createdSellerDTO.getId()))
                .body(createdSellerDTO);
    }    
}
