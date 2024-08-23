package com.colak.springtutorial.service;

import com.colak.springtutorial.helpers.FilterBuilder;
import com.colak.springtutorial.helpers.FilterSortRegister;
import com.colak.springtutorial.helpers.SearchFilters;
import com.colak.springtutorial.repository.ResourceRepository;
import com.colak.springtutorial.repository.support.GenericFilterCriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;
import java.util.List;

@Slf4j
public class GenericService<T, D extends Serializable> {

    @Autowired
    private ResourceRepository<T, D> repository;

    @Autowired
    private FilterBuilder filterBuilder;

    public List<T> getAll() {
        return repository.findAll();
    }


    public List<T> getAll(FilterSortRegister request) {
        SearchFilters search = filterBuilder.getFilters(request.filterAnd(), request.filterOr());

        Query query = new GenericFilterCriteriaBuilder(search).buildQuery();

        return repository.findAllByQuery(query);
    }


    public List<T> getAllSort(FilterSortRegister request) {
        var search = filterBuilder.getFilters(request.filterAnd(), request.filterOr());
        Query query = new GenericFilterCriteriaBuilder(search).buildQuery();

        Sort sort = filterBuilder.getSort(request.orders());
        return repository.findAllByQuerySort(query, sort);
    }

    public Page<T> getPage(FilterSortRegister request) {
        SearchFilters searchFilters = filterBuilder.getFilters(request.filterAnd(), request.filterOr());
        Query query = new GenericFilterCriteriaBuilder(searchFilters).buildQuery();

        PageRequest pageRequest = filterBuilder.getPageable(request.size(), request.page(), request.orders());
        return repository.findAll(query, pageRequest);
    }
}
