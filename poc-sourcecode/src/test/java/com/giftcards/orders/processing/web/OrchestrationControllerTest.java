package com.giftcards.orders.processing.web;

import com.giftcards.orders.model.GiftCardOrder;
import com.giftcards.orders.model.LineItem;
import com.giftcards.orders.processing.OrchestrationService;
import com.giftcards.orders.processing.OrderStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrchestrationControllerTest {


    @MockBean
    OrchestrationService orchestrationService;

    @Autowired
    OrchestrationController orderStateMachineController;
    @Autowired
    TestRestTemplate testRestTemplate;
    @LocalServerPort
    private String port;



    @Test
    public void testProcessOrder() throws  MalformedURLException {
        GiftCardOrder order = new GiftCardOrder("order1");
        List<LineItem> lineItems = new ArrayList<>();
        LineItem lineItem = new LineItem();
        order.setLineItems(lineItems);

        HttpEntity<GiftCardOrder> request = new HttpEntity<>(order);
        OrderStatus orderStatus = new OrderStatus(order.getId());

        Mockito.when(orchestrationService.processOrder(order)).thenReturn(orderStatus);


        ResponseEntity<OrderStatus> response =  testRestTemplate.postForEntity( new URL("http://localhost:" +  port+"/api/orders/orchestrationengine/").toString(),request,OrderStatus.class);
        assertEquals(201,response.getStatusCode().value());
        assertEquals(orderStatus,response.getBody());
    }


    @Test
    public void testGetProcessStatus() throws  MalformedURLException {
        GiftCardOrder order = new GiftCardOrder("order1");
        List<LineItem> lineItems = new ArrayList<>();
        LineItem lineItem = new LineItem();
        lineItem.setItemid("lineitem1");
        order.setLineItems(lineItems);

        OrderStatus orderStatus = new OrderStatus(order.getId());

        Mockito.when(orchestrationService.getOrderStatus(order.getId(),lineItem.getItemid())).thenReturn(orderStatus);


        ResponseEntity<OrderStatus> response =  testRestTemplate.getForEntity( new URL("http://localhost:" +  port+"/api/orders/orchestrationengine/status/order1/lineitem1").toString(),OrderStatus.class);
        assertEquals(200,response.getStatusCode().value());
        assertEquals(orderStatus,response.getBody());
    }


}
