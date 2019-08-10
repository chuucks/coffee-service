package org.codesolt.mongokubernetesdemo.config;

import lombok.extern.java.Log;
import org.codesolt.mongokubernetesdemo.model.Coffee;
import org.codesolt.mongokubernetesdemo.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Log
public class MongoDataLoader {

    @Autowired
    private CoffeeRepository coffeeRepository;

    private Coffee coffee;

    public MongoDataLoader() {
        coffee = Coffee.builder()
                .name("Americano")
                .build();
    }

    @PostConstruct
    private void loadInfo() {
        coffeeRepository.deleteAll();
        log.info("Loading info to Mongo");
        coffeeRepository.save(coffee);
    }
}
