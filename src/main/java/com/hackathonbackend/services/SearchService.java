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
    private final OpenAiService openAiService;

    public SearchService(SearchRepository searchRepo,
                         @Lazy UserService userService,
                         @Lazy ResultMarkService resultMarkService,
                         OpenAiService openAiService) {
        this.searchRepo = searchRepo;
        this.userService = userService;
        this.resultMarkService = resultMarkService;
        this.openAiService = openAiService;
    }

    @Transactional
    public SearchDTO createSearch(Long userId, String query, String type, Double latitude, Double longitude) {
        User user = userService.getUser(userId);

        Search search = new Search();
        search.setUser(user);
        search.setQuery(query);
        search.setType(type);

        search = searchRepo.save(search);

        // Call OpenAI with the query and location
        String aiResponse = openAiService.searchPlaces(query, latitude, longitude);

        List<ResultMarkResponse> resultDTOs = resultMarkService.getResultsBySearchDTO(search);
        return toDTO(search, resultDTOs, aiResponse);
    }

    public SearchDTO getSearchByIdDTO(Long searchId) {
        Search search = searchRepo.findById(searchId)
                .orElseThrow(() -> new RuntimeException("Search not found"));

        List<ResultMarkResponse> results = resultMarkService.getResultsBySearchDTO(search);
        return toDTO(search, results, null);
    }

    public List<SearchDTO> getRecentSearchesDTO(Long userId) {
        List<Search> searches = searchRepo.findTop5ByUserIdOrderByCreatedAtDesc(userId);
        return searches.stream()
                .map(s -> toDTO(s, resultMarkService.getResultsBySearchDTO(s), null))
                .collect(Collectors.toList());
    }

    private SearchDTO toDTO(Search search, List<ResultMarkResponse> results, String aiResponse) {
        return new SearchDTO(
                search.getId(),
                search.getQuery(),
                search.getType(),
                search.getCreatedAt(),
                search.getUser().getId(),
                results,
                aiResponse
        );
    }
}
