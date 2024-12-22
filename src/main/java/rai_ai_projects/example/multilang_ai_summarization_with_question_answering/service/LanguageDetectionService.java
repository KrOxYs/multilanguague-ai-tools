package rai_ai_projects.example.multilang_ai_summarization_with_question_answering.service;

import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LanguageDetectionService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> detectLanguage(String text) {
        String url = "http://localhost:3000/detect-language";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        Map<String, String> requestPayload = Map.of("text", text);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestPayload, headers);

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<Map<String, Object>>() {
                });

        return response.getBody();
    }
}
