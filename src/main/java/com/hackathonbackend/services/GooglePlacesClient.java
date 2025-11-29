package com.hackathonbackend.services;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GooglePlacesClient {
//    @Value("${google.api.key}")
    private String apiKey = "AIzaSyDZFqLU2wL6S-VWkEZLbi8-Gq3C7GIfwhM";

    private final WebClient googleWebClient;

    public GooglePlacesClient(@Qualifier("googleWebClient") WebClient googleWebClient) {
        this.googleWebClient = googleWebClient;
    }

    public Mono<String> textSearch(String query) {
        return googleWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/textsearch/json") // путь после baseUrl
                        .queryParam("query", query)
                        .queryParam("key", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(Throwable::printStackTrace);
    }
}
//@Service
//public class GooglePlacesClient {
//
////private final WebClient webClient;
////
////    public GooglePlacesClient(WebClient.Builder webClientBuilder) {
////        this.webClient = webClientBuilder.baseUrl("https://maps.googleapis.com").build();
////    }
//
////    public Mono<String> searchBurgersInKosice() {
////        String apiKey = "AIzaSyDZFqLU2wL6S-VWkEZLbi8-Gq3C7GIfwhM"; // твой API ключ
////        String query = "burger kosice";
////
////        return webClient.get()
////                .uri(uriBuilder -> uriBuilder
////                        .path("/maps/api/place/textsearch/json")
////                        .queryParam("query", query)
////                        .queryParam("key", apiKey)
////                        .build())
////                .retrieve()
////                .bodyToMono(String.class);
////    }
//
////    public Mono<String> getByFullUrl(String fullUrl) {
////        fullUrl = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=burger+kosice&key=AIzaSyDZFqLU2wL6S-VWkEZLbi8-Gq3C7GIfwhM";
////        return webClient.get()
////                .uri(fullUrl) // полный URL
////                .retrieve()
////                .bodyToMono(String.class)
////                .doOnError(Throwable::printStackTrace);
////    }
//
////    @Value("${google.api.key}")
//    private String apiKey = "AIzaSyDZFqLU2wL6S-VWkEZLbi8-Gq3C7GIfwhM";
//
//    private final WebClient googleWebClient;
//
//    public GooglePlacesClient(@Qualifier("googleWebClient")WebClient googleWebClient) {
//        this.googleWebClient = googleWebClient;
//    }
//
//    public Mono<String> textSearch(String query) {
//        return googleWebClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .scheme("https")
//                        .host("maps.googleapis.com")
//                        .path("/maps/api/place/textsearch/json")
//                        .queryParam("query", query)
//                        .queryParam("key", apiKey)
//                        .build()
//                )
//                .retrieve()
//                .bodyToMono(String.class)
//                .doOnError(Throwable::printStackTrace);
//    }
//}



