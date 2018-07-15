package com.dev.springwebfluxrest.repository;

import com.dev.springwebfluxrest.domain.Category;
import com.dev.springwebfluxrest.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Kevin Neag
 */
public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
