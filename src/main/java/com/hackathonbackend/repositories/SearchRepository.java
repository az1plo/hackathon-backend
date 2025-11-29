package com.hackathonbackend.repositories;

import com.hackathonbackend.models.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Search, Long> {
    List<Search> findTop5ByUserIdOrderByCreatedAtDesc(Long userId);
}