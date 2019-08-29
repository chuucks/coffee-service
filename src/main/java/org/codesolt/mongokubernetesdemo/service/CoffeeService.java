package org.codesolt.mongokubernetesdemo.service;

import lombok.extern.java.Log;
import org.codesolt.mongokubernetesdemo.exception.CoffeeNotFoundException;
import org.codesolt.mongokubernetesdemo.exception.OrderNotFoundException;
import org.codesolt.mongokubernetesdemo.model.Coffee;
import org.codesolt.mongokubernetesdemo.model.Order;
import org.codesolt.mongokubernetesdemo.repository.CoffeeRepository;
import org.codesolt.mongokubernetesdemo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
        Coffee coffee = coffeeRepository.findByName(coffeeName);
        if(coffee != null) {
            return coffee;
        }
        log.severe(String.format("Coffee requested: %s, not found", coffeeName));
        throw new CoffeeNotFoundException("Sorry, we don't have that coffee");
    }

    public Order createOrder(String coffeeName, int quantity) {
        Coffee coffee = getCoffeeInfo(coffeeName);
        return orderRepository.save(
            Order.builder()
                .coffee(coffee)
                .quantity(quantity)
                .total(coffee.getPrice() * quantity)
                .build());
    }

    public Order getOrder(String orderId) {
        try {
            return orderRepository.findById(orderId).get();
        } catch(NoSuchElementException ex) {
            log.severe(String.format("Order requested: %s, not found", orderId));
            throw new OrderNotFoundException("Order not found");
        }
    }

    public Order updateOrder(String orderId, int quantity) {
        Order order = getOrder(orderId);
        order.setQuantity(quantity);
        order.setTotal(order.getCoffee().getPrice() * quantity);
        return orderRepository.save(order);
    }

    public String deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
        return String.format("Order %s was deleted", orderId);
    }
}
