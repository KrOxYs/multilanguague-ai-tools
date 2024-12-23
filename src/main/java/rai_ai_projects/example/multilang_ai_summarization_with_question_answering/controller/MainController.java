package rai_ai_projects.example.multilang_ai_summarization_with_question_answering.controller;

import rai_ai_projects.example.multilang_ai_summarization_with_question_answering.dto.TranslationDTO;
import rai_ai_projects.example.multilang_ai_summarization_with_question_answering.service.LanguageDetectionService;
import rai_ai_projects.example.multilang_ai_summarization_with_question_answering.service.LanguageTranslateService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    public class TranslationRequest {
        private String text;
        private String targetLang;

        // Getters and setters
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTargetLang() {
            return targetLang;
        }

        public void setTargetLang(String targetLang) {
            this.targetLang = targetLang;
        }

    }

    String textWord;
    private final LanguageDetectionService languageDetectionService;
    private final LanguageTranslateService languageTranslateService;

    @Autowired
    public MainController(LanguageDetectionService languageDetectionService,
            LanguageTranslateService languageTranslateService) {
        this.languageDetectionService = languageDetectionService;
        this.languageTranslateService = languageTranslateService;
    }

    @PostMapping("/translate-language")
    public Map<String, Object> detectLanguage(@RequestBody TranslationDTO req) {
        String text = req.text;
        String targetLang = req.targetLang;

        Map<String, Object> detectLang = languageDetectionService.detectLanguage(text);

        if (detectLang != null) {
            String detectedLanguage = (String) detectLang.get("detectedLanguage");

            return languageTranslateService.translateLanguage(text, detectedLanguage,
                    targetLang);

        }
        return null;
    }

}
