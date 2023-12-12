package com.lingua.market.persistence.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class LanguageTest {

    @Test
    public void testGetSetId() {
        Language language = new Language();
        Long id = 1L;
        language.setId(id);
        assertEquals(id, language.getId());
    }

    @Test
    public void testGetSetName() {
        Language language = new Language();
        String name = "Croatian";
        language.setName(name);
        assertEquals(name, language.getName());
    }

}
