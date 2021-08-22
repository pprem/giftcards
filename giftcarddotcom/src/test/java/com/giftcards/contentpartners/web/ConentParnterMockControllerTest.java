package com.giftcards.contentpartners.web;

import com.giftcards.orders.model.GiftCardOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConentParnterMockControllerTest {

    @Autowired
    ContentPartnerMockController vendorServiceMockController;
    @Autowired
    TestRestTemplate testRestTemplate;
    @LocalServerPort
    private String post;

    @Test
    public void testRequestVendorOrderVISA() throws MalformedURLException {
        URL url  = new URL("http://localhost:"+post+"/api/contentpartners/visa");
        GiftCardOrder order = new GiftCardOrder("order1");
       ResponseEntity<GiftCardOrder> response = testRestTemplate.postForEntity(url.toString(),order,GiftCardOrder.class);
       assertEquals(200,response.getStatusCode().value());
        assertEquals(order,response.getBody());

    }


    @Test
    public void testRequestVendorOrderGRUBHUB() throws MalformedURLException {
        URL url  = new URL("http://localhost:"+post+"/api/contentpartners/grubhub");
        GiftCardOrder order = new GiftCardOrder("order1");
        ResponseEntity<GiftCardOrder> response = testRestTemplate.postForEntity(url.toString(),order,GiftCardOrder.class);
        assertEquals(200,response.getStatusCode().value());
        assertEquals(order,response.getBody());

    }
    @Test
    public void testRequestVendorOrderULTA() throws MalformedURLException {
        URL url  = new URL("http://localhost:"+post+"/api/contentpartners/ulta");
        GiftCardOrder order = new GiftCardOrder("order1");
        ResponseEntity<GiftCardOrder> response = testRestTemplate.postForEntity(url.toString(),order,GiftCardOrder.class);
        assertEquals(200,response.getStatusCode().value());
        assertEquals(order,response.getBody());

    }
    @Test
    public void testRequestVendorOrderHOTELDOTCOM() throws MalformedURLException {
        URL url  = new URL("http://localhost:"+post+"/api/contentpartners/hoteldotcom");
        GiftCardOrder order = new GiftCardOrder("order1");
        ResponseEntity<GiftCardOrder> response = testRestTemplate.postForEntity(url.toString(),order,GiftCardOrder.class);
        assertEquals(200,response.getStatusCode().value());
        assertEquals(order,response.getBody());

    }
}
