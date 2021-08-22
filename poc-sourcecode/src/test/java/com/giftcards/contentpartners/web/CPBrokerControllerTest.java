package com.giftcards.contentpartners.web;

import com.giftcards.contentpartners.model.ContentPartner;
import com.giftcards.contentpartners.service.CPBrokerService;
import com.giftcards.orders.model.GiftCardOrder;
import com.giftcards.orders.model.LineItem;
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


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CPBrokerControllerTest {

    @MockBean
    CPBrokerService vendorProxyService;

    @Autowired
    TestRestTemplate testRestTemplate;
    @LocalServerPort
    private String post;

    @Test
    public void registerVendorTest() throws MalformedURLException {
        URL url  = new URL("http://localhost:"+post+"/api/cpproxy/register");
        ContentPartner contentPartner = new ContentPartner();
        URL visaurl  = new URL("http://localhost:"+post+"/api/contentpartners/visa");
        contentPartner.setId("visa");
        contentPartner.setUrl(visaurl);
        contentPartner.setName("VISA inc.");
        Mockito.when(vendorProxyService.registerVendor(contentPartner)).thenReturn(contentPartner);
       ResponseEntity<ContentPartner> response = testRestTemplate.postForEntity(url.toString(), contentPartner, ContentPartner.class);
       assertEquals(200,response.getStatusCode().value());
        assertEquals(contentPartner,response.getBody());

    }

    @Test
    public void testPlaceOrderToVisa() throws MalformedURLException {

        ContentPartner contentPartner = new ContentPartner();
        URL visaurl  = new URL("http://localhost:"+post+"/api/contentpartners/visa");
        contentPartner.setId("visa");
        contentPartner.setUrl(visaurl);
        contentPartner.setName("VISA inc.");
        String vendorid="visa";
        Mockito.when(vendorProxyService.getVendor(vendorid)).thenReturn(contentPartner);
        GiftCardOrder order = new GiftCardOrder("order1");
        List<LineItem> lineItems = new ArrayList<>();
        LineItem lineItem = new LineItem();
        order.setLineItems(lineItems);

        HttpEntity<GiftCardOrder> request = new HttpEntity<>(order);
        OrderStatus orderStatus = new OrderStatus(order.getId());

        URL url  = new URL("http://localhost:"+post+"/api/cpproxy/order/visa");
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
