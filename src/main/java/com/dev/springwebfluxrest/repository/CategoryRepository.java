package com.dev.springwebfluxrest.repository;

import com.dev.springwebfluxrest.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Kevin Neag
 */
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
