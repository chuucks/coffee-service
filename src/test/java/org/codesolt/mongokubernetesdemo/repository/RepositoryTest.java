package org.codesolt.mongokubernetesdemo.repository;

import org.codesolt.mongokubernetesdemo.model.Coffee;
import org.codesolt.mongokubernetesdemo.model.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DataMongoTest
@RunWith(SpringRunner.class)
public class RepositoryTest {

    private Coffee americano;

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Before
    public void loadTestingData() {
        List<Coffee> coffeeList;
        americano = Coffee.builder()
            .name("Americano")
            .description("Hot water to a shot of espresso coffee.")
            .price(2.0)
            .build();

        Coffee cappuccino = Coffee.builder()
                .name("Cappuccino")
                .description("Shot of espresso, then a shot of steamed milk, and finally a layer of frothed, foamy milk.")
                .price(2.5)
                .build();

        coffeeRepository.deleteAll();
        coffeeList = Arrays.asList(americano, cappuccino);
        coffeeRepository.saveAll(coffeeList);
    }

    @Test
    public void findCoffeeByNameTest() {
        Coffee coffee = coffeeRepository.findByName("Americano");
        assertNotNull(coffee);
        assertEquals("Americano", coffee.getName());
        assertEquals(new Double(2.0), coffee.getPrice());
    }

    @Test
    public void findAllCoffeeTest() {
        List<Coffee> coffeeList = coffeeRepository.findAll();
        assertNotNull(coffeeList);
        assertEquals(2, coffeeList.size());
    }

    @Test
    public void saveOrderTest() {
        Order order = orderRepository.save(
                Order.builder()
                        .coffee(americano)
                        .quantity(2)
                        .total(americano.getPrice() * 2)
                        .build());
        assertNotNull(order);
        assertEquals((Double) 4.0, order.getTotal());
    }

    @Test
    public void findOrderByIdTest() {
        Order order = orderRepository.save(
                Order.builder()
                        .coffee(americano)
                        .quantity(2)
                        .total(americano.getPrice() * 2)
                        .build());
        Order retrived = orderRepository.getOrderById(order.getId());
        assertNotNull(retrived);
        assertEquals(order, retrived);
    }

    @Test
    public void updateOrderTest() {
        Order order = orderRepository.save(
                Order.builder()
                        .coffee(americano)
                        .quantity(2)
                        .total(americano.getPrice() * 2)
                        .build());
        assertNotNull(order);
        assertEquals("Americano", order.getCoffee().getName());
        assertEquals(2, order.getQuantity());
        assertEquals(new Double(4.0), order.getTotal());

        order.setQuantity(3);
        order.setTotal(6.0);
        order = orderRepository.save(order);
        assertEquals(3, order.getQuantity());
        assertEquals(new Double(6.0), order.getTotal());
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteOrderTest() {
        Order order = orderRepository.save(
                Order.builder()
                        .coffee(americano)
                        .quantity(2)
                        .total(americano.getPrice() * 2)
                        .build());

        orderRepository.deleteById(order.getId());
        orderRepository.findById(order.getId()).get();
    }
}
