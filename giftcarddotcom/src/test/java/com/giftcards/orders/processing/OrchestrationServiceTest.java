package com.giftcards.orders.processing;

import com.giftcards.orders.model.GiftCardOrder;
import com.giftcards.orders.model.LineItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrchestrationServiceTest {
    @Autowired
    OrchestrationService orderStateMachineService;


    @Test
    public void testOrderStateMachineServiceInstanec(){
        assertNotNull(orderStateMachineService);
    }

    @Test
    public void testOrderProcess(){
        GiftCardOrder order = new GiftCardOrder("order1");
        List<LineItem> lineItems = new ArrayList<>();
        LineItem lineItem = new LineItem();
        order.setLineItems(lineItems);

        OrderStatus status = orderStateMachineService.processOrder(order);
        assertNotNull(status);
    }

    @Test
    public void updateOrderStatus(){
        GiftCardOrder order = new GiftCardOrder("order1");
        List<LineItem> lineItems = new ArrayList<>();
        LineItem lineItem = new LineItem();
        order.setLineItems(lineItems);

        String status = "order is being processed";
        OrderStatus orderStatus = orderStateMachineService.updateOrderStatus("order1",null,status);
        assertNotNull(orderStatus);
    }

    @Test
    public void getOrderStatus(){
        GiftCardOrder order = new GiftCardOrder("order1");
        List<LineItem> lineItems = new ArrayList<>();
        LineItem lineItem = new LineItem();
        order.setLineItems(lineItems);
        String status = "order is being processed";
        OrderStatus orderStatus = orderStateMachineService.updateOrderStatus("order1",null,status);
        OrderStatus resStatus  = orderStateMachineService.getOrderStatus("order1");
        assertEquals(orderStatus,resStatus);
    }
}
