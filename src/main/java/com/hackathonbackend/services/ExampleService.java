package com.hackathonbackend.services;

import com.hackathonbackend.models.entity.Example;
import com.hackathonbackend.repositories.ExampleRepository;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {
    private final ExampleRepository exampleRepository;

    public ExampleService(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    public Example create(String name, String content) {
        Example example = new Example();
        example.setContent(content);
        example.setName(name);

        return exampleRepository.save(example);
    }

}
