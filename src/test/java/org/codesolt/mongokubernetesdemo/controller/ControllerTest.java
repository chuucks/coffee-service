package org.codesolt.mongokubernetesdemo.controller;

import org.codesolt.mongokubernetesdemo.model.Coffee;
import org.codesolt.mongokubernetesdemo.model.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Autowired
    ApplicationContext context;

    private WebTestClient webTestClient;

    @Before
    public void setUp() throws Exception {

        webTestClient = WebTestClient
                .bindToApplicationContext(context)
                .configureClient()
                .baseUrl("/api/coffee-service")
                .build();
    }

    @Test
    @WithMockUser(roles = "CLIENT")
    public void getMenuTest() {
        webTestClient.get()
                .uri("/coffee")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Coffee.class).hasSize(4);
    }

    @Test
    @WithMockUser(roles = "CLIENT")
    public void getCoffeeInfoTest() {
        webTestClient.get()
                .uri("/coffee/{coffee}", "Americano")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Coffee.class);
    }

    @Test
    @WithMockUser(roles = "CLIENT")
    public void createOrderTest() {
        webTestClient.get()
                .uri("/order/{name}/{quantity}", "Americano", 3)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Order.class);
    }

}
