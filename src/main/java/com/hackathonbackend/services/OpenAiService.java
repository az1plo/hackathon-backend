package com.hackathonbackend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {

    private final WebClient webClient;

    public OpenAiService(@Value("${openai.api.key}") String apiKey,
                         @Value("${openai.api.url}") String apiUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public String chat(String userMessage) {
        Map<String, Object> requestBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", List.of(
                        Map.of("role", "system", "content", "You are a helpful assistant."),
                        Map.of("role", "user", "content", userMessage)
                ),
                "max_tokens", 1000
        );

        try {
            Map response = webClient.post()
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            if (response != null && response.containsKey("choices")) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
                if (!choices.isEmpty()) {
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    return (String) message.get("content");
                }
            }
            return "No response from AI";
        } catch (Exception e) {
            return "Error calling OpenAI: " + e.getMessage();
        }
    }

    public String searchPlaces(String query, Double latitude, Double longitude) {
        String systemPrompt = """
            You are a location-based search assistant. The user will provide a search query and their current location (latitude and longitude).
            Your task is to suggest real places that match the query and are near the user's location.
            
            IMPORTANT: You must respond ONLY with a valid JSON array, no other text.
            Each place in the array must have these exact fields:
            - title: name of the place
            - latitude: latitude coordinate as a string
            - longitude: longitude coordinate as a string  
            - description: brief description of why this place matches the query
            
            Return 3-5 places. Example format:
            [{"title":"Place Name","latitude":"48.1234","longitude":"17.1234","description":"Description here"}]
            """;

        String userPrompt = String.format(
            "Search query: %s\nUser location: latitude=%f, longitude=%f\nFind places matching this query near this location.",
            query, latitude, longitude
        );

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        Map.of("role", "system", "content", systemPrompt),
                        Map.of("role", "user", "content", userPrompt)
                ),
                "max_tokens", 2000
        );

        try {
            Map response = webClient.post()
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            if (response != null && response.containsKey("choices")) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
                if (!choices.isEmpty()) {
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    return (String) message.get("content");
                }
            }
            return "[]";
        } catch (Exception e) {
            return "[]";
        }
    }
}

