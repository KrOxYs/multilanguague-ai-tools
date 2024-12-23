package rai_ai_projects.example.multilang_ai_summarization_with_question_answering.dto;

public class TranslationDTO {
    public String text;
    public String targetLang;

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
