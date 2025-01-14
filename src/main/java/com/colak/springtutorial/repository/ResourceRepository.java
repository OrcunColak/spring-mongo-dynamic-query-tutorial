package com.colak.springtutorial.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface ResourceRepository<T, I extends Serializable> extends MongoRepository<T, I> {

    Page<T> findAll(Query query, Pageable pageable);

    List<T> findAllByQuery(Query query);

    List<T> findAllByQuerySort(Query query, Sort sort);
}
