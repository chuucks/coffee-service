package org.codesolt.mongokubernetesdemo.service;

import lombok.extern.java.Log;
import org.codesolt.mongokubernetesdemo.exception.CoffeeNotFoundException;
import org.codesolt.mongokubernetesdemo.model.Coffee;
import org.codesolt.mongokubernetesdemo.model.Order;
import org.codesolt.mongokubernetesdemo.repository.CoffeeRepository;
import org.codesolt.mongokubernetesdemo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<Coffee> getMenu() {
        return coffeeRepository.findAll();
    }

    public Coffee getCoffeeInfo(String coffeeName) {
        return coffeeRepository.findByName(coffeeName);
    }

    public Order createOrder(String coffeeName, int quantity) {
        Coffee coffee = getCoffeeInfo(coffeeName);
        if(coffee != null) {
            return orderRepository.save(
                    Order.builder()
                        .coffee(coffee)
                        .quantity(quantity)
                        .total(coffee.getPrice() * quantity)
                        .build());
        }
        log.severe(String.format("Coffee requested: %s, not found", coffeeName));
        throw new CoffeeNotFoundException("Sorry, we don't have that coffee");
    }

    public String deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
        return String.format("Order %s was deleted", orderId);
    }
}
