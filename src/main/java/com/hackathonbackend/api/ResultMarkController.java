package com.hackathonbackend.api;

import com.hackathonbackend.models.dto.ResultMarkResponse;
import com.hackathonbackend.services.ResultMarkService;
import com.hackathonbackend.services.SearchService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/result-marks")
public class ResultMarkController {

    private final ResultMarkService resultMarkService;
    private final SearchService searchService;

    public ResultMarkController(ResultMarkService resultMarkService,
                                @Lazy SearchService searchService
                                ) {
        this.resultMarkService = resultMarkService;
        this.searchService = searchService;
    }

    @PostMapping
    public ResultMarkResponse createResultMark(@RequestParam Long searchId,
                                               @RequestParam String rationale) {
        var search = searchService.getSearchByIdDTO(searchId).toEntity();
        var mark = resultMarkService.createResult(search, rationale);
        return resultMarkService.toDTO(mark);
    }

    @GetMapping("/search/{searchId}")
    public List<ResultMarkResponse> getResultsBySearch(@PathVariable Long searchId) {
        return resultMarkService.getResultsBySearchDTO(
                searchService.getSearchByIdDTO(searchId).toEntity()
        );
    }
}