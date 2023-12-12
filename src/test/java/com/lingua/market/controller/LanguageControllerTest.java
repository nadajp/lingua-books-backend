package com.lingua.market.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;

import com.lingua.market.web.controller.LanguageController;

@WebMvcTest(LanguageController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class LanguageControllerTest {
    
}
