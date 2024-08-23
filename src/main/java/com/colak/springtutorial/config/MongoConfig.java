package com.colak.springtutorial.config;

import com.colak.springtutorial.repository.ResourceRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(repositoryBaseClass = ResourceRepository.class)
public class MongoConfig {

}
