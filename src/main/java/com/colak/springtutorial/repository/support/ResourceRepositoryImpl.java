package com.colak.springtutorial.repository.support;


import com.colak.springtutorial.repository.ResourceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

public class ResourceRepositoryImpl<T, I extends Serializable> extends SimpleMongoRepository<T, I> implements ResourceRepository<T, I> {

    public static final String QUERY_MUST_NOT_BE_NULL = "Query must not be null!";
    private final MongoOperations mongoOperations;
    private final MongoEntityInformation<T, I> entityInformation;

    public ResourceRepositoryImpl(MongoEntityInformation<T, I> entityInformation, MongoOperations mongoOperations) {
        super(entityInformation, mongoOperations);

        this.entityInformation = entityInformation;
        this.mongoOperations = mongoOperations;
    }

    @Override
    public Page<T> findAll(final Query query, final Pageable pageable) {
        Assert.notNull(query, QUERY_MUST_NOT_BE_NULL);

        long total = mongoOperations.count(query, entityInformation.getJavaType(), entityInformation.getCollectionName());
        List<T> content = mongoOperations.find(query.with(pageable), entityInformation.getJavaType(), entityInformation.getCollectionName());

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public List<T> findAllByQuery(Query query) {
        Assert.notNull(query, QUERY_MUST_NOT_BE_NULL);
        return mongoOperations.find(query, entityInformation.getJavaType(), entityInformation.getCollectionName());
    }

    @Override
    public List<T> findAllByQuerySort(Query query, Sort sort) {
        Assert.notNull(query, QUERY_MUST_NOT_BE_NULL);
        return mongoOperations.find(query.with(sort), entityInformation.getJavaType(), entityInformation.getCollectionName());
    }
}
