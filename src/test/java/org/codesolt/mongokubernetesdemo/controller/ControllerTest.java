package org.codesolt.mongokubernetesdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.codesolt.mongokubernetesdemo.model.Coffee;
import org.codesolt.mongokubernetesdemo.model.Order;
import org.codesolt.mongokubernetesdemo.repository.CoffeeRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @LocalServerPort
    private int port;

    private ObjectMapper objectMapper = new ObjectMapper();

    private final String URL = "http://localhost:";
    private final String PATH = "/api/coffee-service";

    CollectionType coffeeListType =
            objectMapper.getTypeFactory().constructCollectionType(List.class, Coffee.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getMenuTest() throws IOException {
        System.out.println(port);
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("client", "client")
                .getForEntity(URL + port + PATH + "/coffee", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Coffee> coffeeList = objectMapper.readValue(response.getBody(), coffeeListType);
        assertNotNull(coffeeList);
        assertEquals(4, coffeeList.size());
        assertNotNull(coffeeList.get(0).getName());
    }

    @Test
    public void getCoffeeInfoTest() throws IOException {
        System.out.println(port);
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("client", "client")
                .getForEntity(URL + port + PATH + "/coffee/Americano", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Coffee coffee = objectMapper.readValue(response.getBody(), Coffee.class);
        assertNotNull(coffee);
        assertEquals("Americano", coffee.getName());
    }
}
