package com.lingua.market.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lingua.market.service.SellerService;
import com.lingua.market.web.controller.SellerController;
import com.lingua.market.web.dto.SellerDTO;

@WebMvcTest(SellerController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class SellerControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SellerService sellerService;

    SellerController sellerController;

    @Test
    public void getAllSellersTest() throws Exception {
        when(sellerService.getAllSellers())
        .thenReturn(this.getMockSellers());

         MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/sellers"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        String response = result.getResponse().getContentAsString();
        assertEquals(new ObjectMapper().writeValueAsString(this.getMockSellers()), response);
    }

    @Test
    public void getSellerByAuthIdTest() throws Exception {
        when(sellerService.getSellerByAuthUser(Mockito.anyString()))
        .thenReturn(this.getMockSellers().get(0));

        MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/sellers/auth|1233434343"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        String response = result.getResponse().getContentAsString();
        assertEquals(new ObjectMapper().writeValueAsString(this.getMockSellers().get(0)), response);
    }

    @Test
    public void createSellerTest() throws Exception {
        when(sellerService.createSeller(Mockito.any(SellerDTO.class)))
        .thenReturn(this.getMockSellers().get(0));

        MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/sellers")
            .contentType("application/json")
            .content(new ObjectMapper().writeValueAsString(this.getMockSellers().get(0))))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn();

        String response = result.getResponse().getContentAsString();
        assertEquals(new ObjectMapper().writeValueAsString(this.getMockSellers().get(0)), response);
    }

    private List<SellerDTO> getMockSellers() {
        SellerDTO seller = new SellerDTO();
        seller.setAuthUser("auth|1233434343");
        seller.setCity("Naples");
        seller.setState("FL");
        seller.setCountry("USA");
        seller.setPostalCode("34102");
        seller.setStripeAccountId("acct_1JQ4Zz2eZvKYlo2C");
        seller.setStripeStatus("verified");
        return List.of(seller);
    }
}
