package com.hackathonbackend.services;

import com.hackathonbackend.models.dto.ResultMarkResponse;
import com.hackathonbackend.models.dto.SearchDTO;
import com.hackathonbackend.models.entity.Search;
import com.hackathonbackend.models.entity.User;
import com.hackathonbackend.repositories.SearchRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final SearchRepository searchRepo;
    private final UserService userService;
    private final ResultMarkService resultMarkService;

    public SearchService(SearchRepository searchRepo,
                         @Lazy UserService userService,
                         @Lazy ResultMarkService resultMarkService) {
        this.searchRepo = searchRepo;
        this.userService = userService;
        this.resultMarkService = resultMarkService;
    }

    @Transactional
    public SearchDTO createSearch(Long userId, String query, String type) {
        User user = userService.getUser(userId);

        Search search = new Search();
        search.setUser(user);
        search.setQuery(query);
        search.setType(type);

        search = searchRepo.save(search);


        List<ResultMarkResponse> resultDTOs = resultMarkService.getResultsBySearchDTO(search);
        return toDTO(search, resultDTOs);
    }

    public SearchDTO getSearchByIdDTO(Long searchId) {
        Search search = searchRepo.findById(searchId)
                .orElseThrow(() -> new RuntimeException("Search not found"));

        List<ResultMarkResponse> results = resultMarkService.getResultsBySearchDTO(search);
        return toDTO(search, results);
    }

    public List<SearchDTO> getRecentSearchesDTO(Long userId) {
        List<Search> searches = searchRepo.findTop5ByUserIdOrderByCreatedAtDesc(userId);
        return searches.stream()
                .map(s -> toDTO(s, resultMarkService.getResultsBySearchDTO(s)))
                .collect(Collectors.toList());
    }

    private SearchDTO toDTO(Search search, List<ResultMarkResponse> results) {
        return new SearchDTO(
                search.getId(),
                search.getQuery(),
                search.getType(),
                search.getCreatedAt(),
                search.getUser().getId(),
                results
        );
    }
}
