package rai_ai_projects.example.multilang_ai_summarization_with_question_answering.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rai_ai_projects.example.multilang_ai_summarization_with_question_answering.service.LanguageDetectionService;

@RestController
public class MainController {

    private final LanguageDetectionService languageDetectionService;

    @Autowired
    public MainController(LanguageDetectionService languageDetectionService) {
        this.languageDetectionService = languageDetectionService;
    }

    @PostMapping("/detect-language")
    public Map<String, Object> detectLanguage(@RequestBody String text) {
        return languageDetectionService.detectLanguage(text);
    }

}
