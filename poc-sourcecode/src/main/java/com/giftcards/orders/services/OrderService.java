package com.giftcards.orders.services;

import com.giftcards.orders.model.GiftCardOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    Map<String,GiftCardOrder> orders ;
    public OrderService(){
        orders = new HashMap<>();
    }

    public GiftCardOrder createOrder(GiftCardOrder order) {
        orders.put(order.getId(),order);
        return order;
    }

    public GiftCardOrder getOrder(String id) {
        return orders.get(id);
    }

    public List<GiftCardOrder> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public void deleteAllOrders() {
        orders.clear();
    }
}
