package org.codesolt.mongokubernetesdemo.service;

import org.codesolt.mongokubernetesdemo.exception.OrderNotFoundException;
import org.codesolt.mongokubernetesdemo.model.Coffee;
import org.codesolt.mongokubernetesdemo.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private CoffeeService coffeeService;

    @Test
    public void getMenu() {
        List<Coffee> menu = coffeeService.getMenu();
        assertNotNull(menu);
        assertNotEquals(4, menu.size());
    }

    @Test
    public void getCoffeeInfoTest() {
        Coffee coffee = coffeeService.getCoffeeInfo("Americano");
        assertNotNull(coffee);
        assertEquals("Americano", coffee.getName());
    }

    @Test
    public void createOrderTest() {
        Order order = coffeeService.createOrder("Americano", 2);
        assertNotNull(order);
        assertEquals((Double) 4.0, order.getTotal());
    }

    @Test
    public void updateOrderTest() {
        Order order = coffeeService.createOrder("Americano", 2);
        assertNotNull(order);
        assertEquals((Double) 4.0, order.getTotal());

        order = coffeeService.updateOrder(order.getId(), 3);
        assertNotNull(order);
        assertEquals((Double) 6.0, order.getTotal());
    }

    @Test(expected = OrderNotFoundException.class)
    public void deleteOrderTest() {
        Order order = coffeeService.createOrder("Americano", 2);
        assertNotNull(order);
        assertEquals((Double) 4.0, order.getTotal());

        coffeeService.deleteOrder(order.getId());
        coffeeService.getOrder(order.getId());
    }
}
