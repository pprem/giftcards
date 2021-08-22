package com.giftcards.orders.services;

import com.giftcards.orders.model.GiftCardOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @BeforeEach
    public void init(){
        orderService.deleteAllOrders();
    }

    @Test
    public void testCreateOrder(){
        GiftCardOrder order = new GiftCardOrder();
        GiftCardOrder res =   orderService.createOrder(order);
        assertEquals(order,res);
    }

    @Test
    public void testGetOrder(){
        GiftCardOrder order =  new GiftCardOrder("order1");
        orderService.createOrder(order);
        GiftCardOrder res = orderService.getOrder(order.getId());
        assertEquals(order,res);
    }

    @Test
    public void testGetAllOrders(){
        GiftCardOrder order1 = new GiftCardOrder("order1");
        GiftCardOrder order2 = new GiftCardOrder("order2");
        GiftCardOrder order3 = new GiftCardOrder("order3");
        List<GiftCardOrder> orders = new ArrayList<>();
        orders.add( new GiftCardOrder("order1"));
        orders.add( new GiftCardOrder("order2"));
        orders.add( new GiftCardOrder("order3"));

        orderService.createOrder(order1);
        orderService.createOrder(order2);
        orderService.createOrder(order3);
        List<GiftCardOrder> res = orderService.getAllOrders();
        assertEquals(orders.size(),res.size() );
        assertTrue( orders.containsAll(res) && res.containsAll(orders));
    }

}
