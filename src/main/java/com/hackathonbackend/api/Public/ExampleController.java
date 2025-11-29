package com.hackathonbackend.api.Public;

import com.hackathonbackend.services.ExampleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/example")
public class ExampleController {

    private final ExampleService exampleService;
    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }
    @PostMapping
    public void postExample(@RequestParam String content, @RequestParam String name) {
        exampleService.create(name,content);
    }
}
