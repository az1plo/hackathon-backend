package com.hackathonbackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient googleWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://maps.googleapis.com/maps/api/place")
                .build();
    }
}
