package com.giftcards.orders.web;

import com.giftcards.orders.model.GiftCardOrder;
import com.giftcards.orders.services.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    OrderController orderController;
    @LocalServerPort
    private String port;

    @MockBean
    OrderService orderService;

    @Test
    public void testCreateOrderUsingRest() throws MalformedURLException {

        URL url = new URL("http://localhost:"+port+"/api/orders");
        GiftCardOrder order = new GiftCardOrder("order1");
        HttpEntity<GiftCardOrder> request = new HttpEntity<>(order);
        Mockito.when(orderService.createOrder(order)).thenReturn(new GiftCardOrder("order1"));
        ResponseEntity<GiftCardOrder> result = testRestTemplate.postForEntity(url.toString(),request,GiftCardOrder.class);
        assertEquals(201,result.getStatusCode().value());
        assertEquals(new GiftCardOrder("order1"),result.getBody());


    }

    @Test
    public void testGetOrderRest() throws MalformedURLException {
        URL url = new URL("http://localhost:"+port+"/api/orders/123");
        GiftCardOrder order = new GiftCardOrder("123");
        Mockito.when(orderService.getOrder("123")).thenReturn(new GiftCardOrder("123"));
        ResponseEntity<GiftCardOrder> responseEntity = testRestTemplate.getForEntity(url.toString(),GiftCardOrder.class);
        assertEquals(200,responseEntity.getStatusCode().value());
        assertEquals(order,responseEntity.getBody());


    }

    @Test
    public void testGetAllOrderRest() throws MalformedURLException {
        URL url = new URL("http://localhost:"+port+"/api/orders/");

        List<GiftCardOrder> orders = new ArrayList<>();
        orders.add(new GiftCardOrder("1231"));
        orders.add(new GiftCardOrder("1232"));
        orders.add(new GiftCardOrder("1233"));
        Mockito.when(orderService.getAllOrders()).thenReturn(orders);
        ResponseEntity<List<GiftCardOrder>> response = testRestTemplate.exchange(
                url.toString(), HttpMethod.GET,null,new ParameterizedTypeReference<List<GiftCardOrder>>() {});

        assertEquals(200,response.getStatusCode().value());
        assertEquals(orders,response.getBody());


    }


}
