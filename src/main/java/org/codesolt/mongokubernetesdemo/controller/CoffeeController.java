package org.codesolt.mongokubernetesdemo.controller;

import org.codesolt.mongokubernetesdemo.model.Coffee;
import org.codesolt.mongokubernetesdemo.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coffee-service")
public class CoffeeController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @GetMapping("/coffee/")
    public List<Coffee> getCoffee() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/coffee/{id}")
    public void getCoffeeInfo(@PathVariable("id") String id) {}

    @PostMapping("/coffee/")
    public void createOrder(@RequestBody Object list) {}
}
