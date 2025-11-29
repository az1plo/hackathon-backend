package com.hackathonbackend.models.dto;

import java.util.List;

public class UserDTO {
    private Long id;
    private String name;
    private List<SearchDTO> searches;

    public UserDTO(Long id, String name) {}

    public UserDTO(Long id, String name, List<SearchDTO> searches) {
        this.id = id;
        this.name = name;
        this.searches = searches;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<SearchDTO> getSearches() { return searches; }
    public void setSearches(List<SearchDTO> searches) { this.searches = searches; }
}

