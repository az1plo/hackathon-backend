package com.hackathonbackend.api;

import com.hackathonbackend.models.dto.SearchDTO;
import com.hackathonbackend.models.dto.CreateSearchRequest;
import com.hackathonbackend.services.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/searches")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public SearchDTO createSearch(@RequestBody CreateSearchRequest request) {
        if (request.getUserId() == null) {
            throw new IllegalArgumentException("User ID is required");
        }
        if (request.getLatitude() == null || request.getLongitude() == null) {
            throw new IllegalArgumentException("Latitude and longitude are required");
        }
        return searchService.createSearch(
                request.getUserId(),
                request.getQuery(),
                request.getType(),
                request.getLatitude(),
                request.getLongitude()
        );
    }

    @GetMapping("/history")
    public List<SearchDTO> getRecentSearches(@RequestParam Long userId) {
        return searchService.getRecentSearchesDTO(userId);
    }
}
