package rai_ai_projects.example.multilang_ai_summarization_with_question_answering.service;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class LanguageTranslateService {

    public final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> translateLanguage(String text, String sourceLanguage, String targetLanguage) {
        String url = "http://127.0.0.1:8000/translate";

        // Correct HttpHeaders import
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        Map<String, String> requestPayload = Map.of(
                "text", text,
                "source_lang", sourceLanguage,
                "target_lang", targetLanguage);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestPayload, headers);

        try {
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    });

            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.err.println("HTTP Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw new RuntimeException("Failed to translate language", e);
        } catch (RestClientException e) {
            System.err.println("RestClientException: " + e.getMessage());
            throw new RuntimeException("An error occurred during the API call", e);
        }
    }
}
