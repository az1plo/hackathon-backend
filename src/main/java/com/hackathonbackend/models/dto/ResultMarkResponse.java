package com.hackathonbackend.models.dto;

public class ResultMarkResponse {
    private Long id;
    private Long searchId;
    private String locationName;
    private String rationale;

    public ResultMarkResponse(Long id, Long searchId, String locationName, String rationale) {
        this.id = id;
        this.searchId = searchId;
        this.locationName = locationName;
        this.rationale = rationale;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSearchId() { return searchId; }
    public void setSearchId(Long searchId) { this.searchId = searchId; }

    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }

    public String getRationale() { return rationale; }
    public void setRationale(String rationale) { this.rationale = rationale; }
}
