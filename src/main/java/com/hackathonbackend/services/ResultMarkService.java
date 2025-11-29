package com.hackathonbackend.services;

import com.hackathonbackend.models.dto.ResultMarkResponse;
import com.hackathonbackend.models.entity.ResultMark;
import com.hackathonbackend.models.entity.Search;
import com.hackathonbackend.repositories.ResultMarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultMarkService {

    private final ResultMarkRepository resultMarkRepo;

    public ResultMarkService(ResultMarkRepository resultMarkRepo) {
        this.resultMarkRepo = resultMarkRepo;
    }

    public ResultMark createResult(Search search, String rationale) {
        ResultMark mark = new ResultMark();
        mark.setSearch(search);
        mark.setRationale(rationale);
        return resultMarkRepo.save(mark);
    }

    public List<ResultMarkResponse> getResultsBySearchDTO(Search search) {
        return resultMarkRepo.findBySearch(search)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ResultMarkResponse toDTO(ResultMark mark) {
        return new ResultMarkResponse(
                mark.getId(),
                mark.getSearch().getId(),
                "location",
                mark.getRationale()
        );
    }
}
