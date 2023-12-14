package com.lingua.market.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lingua.market.service.SellerService;
import com.lingua.market.web.dto.SellerDTO;
import com.lingua.market.web.exception.SellerAlreadyExistsException;

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
    @GetMapping("/{authUser}")
    public ResponseEntity<SellerDTO> getSellerByAuthId(@PathVariable(value = "authUser") String authUser) {
        return ResponseEntity.ok().body(sellerService.getSellerByAuthUser(authUser));
    }

    @PostMapping(consumes = {"application/json"}) 
    public ResponseEntity<SellerDTO> createSeller(@Validated @RequestBody SellerDTO sellerDTO) {
        SellerDTO createdSeller;
        try {
            createdSeller = sellerService.createSeller(sellerDTO);
            return ResponseEntity.created(URI.create("/sellers/" + createdSeller.getId()))
                .body(createdSeller);
        } catch (SellerAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } 
    }    

    @PutMapping("/{authUser}")
    public ResponseEntity<SellerDTO> updateSeller(@PathVariable String authUser, @RequestBody SellerDTO sellerInfo) {
        SellerDTO updatedSeller = sellerService.updateSellerInfo(authUser, sellerInfo);
        return ResponseEntity.ok().body(updatedSeller);
    }
}
