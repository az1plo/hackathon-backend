package com.hackathonbackend.controller;

import com.hackathonbackend.services.GooglePlacesClient;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlacesController {

    private final GooglePlacesClient googlePlacesClient;

    @GetMapping("/search")
    public Mono<String> fullUrl(@RequestParam String query) {
        return googlePlacesClient.textSearch(query);
    }
}
