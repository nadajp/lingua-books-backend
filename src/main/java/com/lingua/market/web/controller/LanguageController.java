package com.lingua.market.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lingua.market.persistence.dao.LanguageRepository;
import com.lingua.market.persistence.model.Language;
import com.lingua.market.web.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/languages")
public class LanguageController {
    
    private LanguageRepository languageRepository;

    public LanguageController(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @GetMapping
    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    @PostMapping(consumes = {"application/json"})
    public Language createLanguage(@Validated @RequestBody Language language) {
        return languageRepository.save(language);
    }

    @PostMapping("/bulk")
    public List<Language> createLanguages(@Validated @RequestBody List<Language> languages) {
        return languageRepository.saveAll(languages);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable(value = "id") Long languageId,
                                                @Validated @RequestBody Language languageDetails) {
        Language language = languageRepository.findById(languageId)
                .orElseThrow(() -> new ResourceNotFoundException("Language not found for this id :: " + languageId));

        language.setName(languageDetails.getName());

        final Language updatedLanguage = languageRepository.save(language);
        return ResponseEntity.ok(updatedLanguage);
    }

    @DeleteMapping("/bulk")
    public void deleteLanguages(@RequestBody List<Long> ids) {
        ids.forEach(id -> languageRepository.deleteById(id));
    }
}
