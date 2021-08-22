package com.giftcards.products.web;

import com.giftcards.products.models.Product;
import com.giftcards.products.services.ProductService;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductManagerControllerTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;



    @MockBean
    ProductService productService;




    @Test
    public void testCreateProdcut() throws Exception {

        Product product = new Product("123","VirtualVisa");
        HttpEntity<Product> request = new HttpEntity<>(product);
        Mockito.when(productService.addProduct(product)).thenReturn(new Product("123","VirtualVisa"));
        ResponseEntity<Product> response = restTemplate.postForEntity(
                new URL("http://localhost:" +  port+"/api/products/").toString(), request, Product.class);
        assertEquals(201,response.getStatusCode().value());
        assertEquals(product,response.getBody());


    }
    @Test
    public void testGetProduct() throws Exception {

        Mockito.when(productService.getProduct("123")).thenReturn(new Product("123", "test"));

        URL url =  new URL("http://localhost:" +  port+"/api/products/123");
        ResponseEntity<Product> response = restTemplate.getForEntity(
                url.toString(), Product.class);

        assertEquals(200,response.getStatusCode().value());
        assertEquals(new Product("123", "test"),response.getBody());



    }

    @Test
    public void testGetProducts() throws Exception {

        List<Product> products = new ArrayList<>();
        products.add(new Product("123", "product1"));
        products.add(new Product("124", "product2"));
        products.add(new Product("125", "product3"));
        products.add(new Product("126", "product3"));
        products.add(new Product("127", "product4"));

        Mockito.when(productService.getAllProducts()).thenReturn(products);

        URL url =  new URL("http://localhost:" +  port+"/api/products/");

        ResponseEntity<List<Product>> response = restTemplate.exchange(
                url.toString(), HttpMethod.GET,null,new ParameterizedTypeReference<List<Product>>() {});

        assertEquals(200,response.getStatusCode().value());
        assertEquals(products,response.getBody());


    }
}
