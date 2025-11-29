package com.hackathonbackend.models.dto;

import com.hackathonbackend.models.entity.Search;

import java.time.LocalDateTime;
import java.util.List;

public class SearchDTO {
    private Long id;
    private String query;
    private String type;
    private LocalDateTime createdAt;
    private Long userId;
    private String aiResponse;

    private List<ResultMarkResponse> results;

    public SearchDTO(Long id, String query, String type, LocalDateTime createdAt, Long userId, List<ResultMarkResponse> results, String aiResponse) {
        this.id = id;
        this.query = query;
        this.type = type;
        this.createdAt = createdAt;
        this.userId = userId;
        this.results = results;
        this.aiResponse = aiResponse;
    }

    public Search toEntity() {
        Search search = new Search();
        search.setId(this.id);
        search.setQuery(this.query);
        search.setType(this.type);
        search.setCreatedAt(this.createdAt != null ? this.createdAt : LocalDateTime.now());
        return search;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<ResultMarkResponse> getResults() { return results; }
    public void setResults(List<ResultMarkResponse> results) { this.results = results; }

    public String getAiResponse() { return aiResponse; }
    public void setAiResponse(String aiResponse) { this.aiResponse = aiResponse; }
}
