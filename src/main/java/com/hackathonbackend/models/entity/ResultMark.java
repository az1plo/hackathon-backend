package com.hackathonbackend.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "result_mark")
public class ResultMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "search_id")
    private Search search;

    private String rationale;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Search getSearch() { return search; }
    public void setSearch(Search search) { this.search = search; }

    public String getRationale() { return rationale; }
    public void setRationale(String rationale) { this.rationale = rationale; }
}
