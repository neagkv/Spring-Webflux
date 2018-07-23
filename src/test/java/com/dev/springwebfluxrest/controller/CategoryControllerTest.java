package com.dev.springwebfluxrest.controller;

import com.dev.springwebfluxrest.domain.Category;
import com.dev.springwebfluxrest.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.junit.Assert.*;
import static org.springframework.test.web.reactive.server.WebTestClient.bindToController;

/**
 * @author Kevin Neag
 */
public class CategoryControllerTest {

    WebTestClient.ControllerSpec webTestClient;
    CategoryController categoryController;
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {

        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryController = new CategoryController(categoryRepository);
        webTestClient = bindToController(CategoryController.class);
    }

    @Test
    public void list() {
        BDDMockito.given(categoryRepository.findAll())
                .willReturn(Flux.just(Category.builder().description("Cat1").build(),
                        Category.builder().description("Cat2").build()));

        webTestClient.get()
                .uri
    }

    @Test
    public void getById() {
    }
}