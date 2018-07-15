package com.dev.springwebfluxrest.bootstrap;

import com.dev.springwebfluxrest.domain.Category;
import com.dev.springwebfluxrest.domain.Vendor;
import com.dev.springwebfluxrest.repository.CategoryRepository;
import com.dev.springwebfluxrest.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

/**
 * @author Kevin Neag
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(categoryRepository.count().block() == 0){
            //load data
            System.out.println("#### Loading Boostrap Data ######");

            categoryRepository.save(Category.builder().description("fruits").build()).block();
            categoryRepository.save(Category.builder().description("nuts").build()).block();
            categoryRepository.save(Category.builder().description("breads").build()).block();
            categoryRepository.save(Category.builder().description("meats").build()).block();
            categoryRepository.save(Category.builder().description("eggs").build()).block();

            System.out.println("Loaded Categories: " + categoryRepository.count().block());

            vendorRepository.save(Vendor.builder().firstName("Joe").lastName("Buck").build()).block();
            vendorRepository.save(Vendor.builder().firstName("Micheal").lastName("Weston").build()).block();
            vendorRepository.save(Vendor.builder().firstName("Sam").lastName("Smith").build()).block();
            vendorRepository.save(Vendor.builder().firstName("Frank").lastName("Wright").build()).block();
            vendorRepository.save(Vendor.builder().firstName("Joe").lastName("Brown").build()).block();

            System.out.println("Loaded Vendors: " + vendorRepository.count().block());
        }

    }
}
