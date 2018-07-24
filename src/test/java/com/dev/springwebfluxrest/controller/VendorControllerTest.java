package com.dev.springwebfluxrest.controller;

import com.dev.springwebfluxrest.domain.Category;
import com.dev.springwebfluxrest.domain.Vendor;
import com.dev.springwebfluxrest.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author Kevin Neag
 */
public class VendorControllerTest {

    WebTestClient webTestClient;
    VendorRepository vendorRepository;
    VendorController vendorController;

    @Before
    public void setUp() throws Exception {

        vendorRepository = Mockito.mock(VendorRepository.class);
        vendorController = new VendorController(vendorRepository);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }


    @Test
    public void list() {
        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(Vendor.builder().firstName("Name1").lastName("LastName1").build(),
                        Vendor.builder().firstName("Name2").lastName("LastName2").build()));

        webTestClient.get()
                .uri("/api/v1/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    public void getById() {
        BDDMockito.given(vendorRepository.findById("someid"))
                .willReturn(Mono.just(Vendor.builder().firstName("Name").lastName("LastName").build()));

        webTestClient.get()
                .uri("/api/v1/vendors/someid")
                .exchange()
                .expectBody(Vendor.class);
    }


    @Test
    public void testCreateVendor() {
        BDDMockito.given(vendorRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Vendor.builder().build()));

        Mono<Vendor>vendorToSaveMono = Mono.just(Vendor.builder().firstName("Bryan").lastName("Green").build());

        webTestClient.post()
                .uri("api/v1/vendors")
                .body(vendorToSaveMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }


    @Test
    public void testUpdate() {
        BDDMockito.given(vendorRepository.save(any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));
        Mono<Vendor> vendorToUpdateMono = Mono.just(Vendor.builder().firstName("Bryan").lastName("Green").build());

        webTestClient.put()
                .uri("api/v1/vendors/hjhjhj")
                .body(vendorToUpdateMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

}
