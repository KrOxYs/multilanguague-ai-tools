package rai_ai_projects.example.multilang_ai_summarization_with_question_answering.controller;

import rai_ai_projects.example.multilang_ai_summarization_with_question_answering.dto.TranslationDTO;
import rai_ai_projects.example.multilang_ai_summarization_with_question_answering.service.LanguageDetectionService;
import rai_ai_projects.example.multilang_ai_summarization_with_question_answering.service.LanguageSummaryService;
import rai_ai_projects.example.multilang_ai_summarization_with_question_answering.service.LanguageTranslateService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    String textWord;
    private final LanguageDetectionService languageDetectionService;
    private final LanguageTranslateService languageTranslateService;
    private final LanguageSummaryService languageSummaryService;

    @Autowired
    public MainController(LanguageDetectionService languageDetectionService,
            LanguageTranslateService languageTranslateService, LanguageSummaryService languageSummaryService) {
        this.languageDetectionService = languageDetectionService;
        this.languageTranslateService = languageTranslateService;
        this.languageSummaryService = languageSummaryService;
    }

    @PostMapping("/summarize")
    public Map<String, Object> detectLanguage(@RequestBody TranslationDTO req) {
        String text = req.text;

        Map<String, Object> detectLang = languageDetectionService.detectLanguage(text);

        String detectedLanguage = (String) detectLang.get("detectedLanguage");
        System.out.println("Detected language: " + detectedLanguage);
        // If the detected language is English, return the original text
        if (text != null && detectedLanguage.equals("en")) {
            Map<String, Object> summarize = languageSummaryService.summarizeLanguage(text);
            return summarize;
        } else {
            // translation
            Map<String, Object> translation = languageTranslateService.translateLanguage(text, detectedLanguage);
            // summary
            // Map<String, Object> summarize =
            // languageSummaryService.summarizeLanguage((String)
            // translation.get("translation"));
            String translatedText = (String) translation.get("translation");
            // System.out.println("Translation: " + translation.get("translation"));
            if (translatedText != null) {
                Map<String, Object> summarize = languageSummaryService.summarizeLanguage(translatedText);
                return summarize;
            }
            return null;
        }

    }

}
