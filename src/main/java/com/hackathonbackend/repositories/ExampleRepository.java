package com.hackathonbackend.repositories;

import com.hackathonbackend.models.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Long> {

}
