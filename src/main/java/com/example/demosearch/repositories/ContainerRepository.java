package com.example.demosearch.repositories;

import com.example.demosearch.model.Container;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContainerRepository extends MongoRepository<Container, String> {
}
