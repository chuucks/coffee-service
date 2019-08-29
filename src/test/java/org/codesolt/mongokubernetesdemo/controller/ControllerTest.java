package org.codesolt.mongokubernetesdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.codesolt.mongokubernetesdemo.model.Coffee;
import org.codesolt.mongokubernetesdemo.model.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @LocalServerPort
    private int port;

    private ObjectMapper objectMapper = new ObjectMapper();

    private String url;

    CollectionType coffeeListType =
            objectMapper.getTypeFactory().constructCollectionType(List.class, Coffee.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setBaseURL() {
        url = "http://localhost:" + port + "/api/coffee-service";
    }

    @Test
    public void getMenuTest() throws IOException {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("client", "client")
                .getForEntity(url + "/coffee", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Coffee> coffeeList = objectMapper.readValue(response.getBody(), coffeeListType);
        assertNotNull(coffeeList);
        assertEquals(4, coffeeList.size());
        assertNotNull(coffeeList.get(0).getName());
    }

    @Test
    public void getCoffeeInfoTest() throws IOException {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("client", "client")
                .getForEntity(url + "/coffee/Americano", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Coffee coffee = objectMapper.readValue(response.getBody(), Coffee.class);
        assertNotNull(coffee);
        assertEquals("Americano", coffee.getName());
    }

    @Test
    public void createOrderTest() throws IOException {
        ResponseEntity<String> createResponse = restTemplate
                .withBasicAuth("client", "client")
                .postForEntity(url + "/order/Americano/3", null, String.class);
        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());

        Order createOrder = objectMapper.readValue(createResponse.getBody(), Order.class);
        assertNotNull(createOrder);
        assertEquals((Double) 6.0, createOrder.getTotal());
    }

    @Test
    public void readOrderTest() throws IOException {
        ResponseEntity<String> createResponse = restTemplate
                .withBasicAuth("client", "client")
                .postForEntity(url + "/order/Americano/3", null, String.class);
        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());

        Order createOrder = objectMapper.readValue(createResponse.getBody(), Order.class);

        ResponseEntity<String> readResponse = restTemplate
                .withBasicAuth("client", "client")
                .getForEntity(url + "/order/" + createOrder.getId(), String.class);
        assertEquals(HttpStatus.OK, readResponse.getStatusCode());

        Order readOrder = objectMapper.readValue(createResponse.getBody(), Order.class);
        assertNotNull(readOrder);
        assertEquals(createOrder, readOrder);
    }
}
