package org.codesolt.mongokubernetesdemo.config;

import lombok.extern.java.Log;
import org.codesolt.mongokubernetesdemo.model.Coffee;
import org.codesolt.mongokubernetesdemo.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
@Log
public class MongoDataLoader {

    @Autowired
    private CoffeeRepository coffeeRepository;

    private List<Coffee> coffeList;

    public MongoDataLoader() {
        Coffee americano = Coffee.builder()
                .name("Americano")
                .description("Hot water to a shot of espresso coffee.")
                .price(2.0)
                .build();

        Coffee espresso = Coffee.builder()
                .name("Espresso")
                .description("Shoot boiling water under high pressure through finely ground up coffee beans and then pour into a tiny mug.")
                .price(1.5)
                .build();

        Coffee cappuccino = Coffee.builder()
                .name("Cappuccino")
                .description("Shot of espresso, then a shot of steamed milk, and finally a layer of frothed, foamy milk.")
                .price(2.5)
                .build();

        Coffee latte = Coffee.builder()
                .name("Latte")
                .description("Steamed (or scolded) milk and a single shot of coffee")
                .price(2.5)
                .build();

        coffeList = Arrays.asList(americano, espresso, cappuccino, latte);
    }

    @PostConstruct
    private void loadInfo() {
        log.info(String.format("Loading info to Mongo: %s", coffeList.toString()));
        coffeeRepository.deleteAll();
        coffeeRepository.saveAll(coffeList);
        log.info("Load to Mongo succeeded");
    }
}
