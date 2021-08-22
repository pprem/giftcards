package com.giftcards.products.services;

import com.giftcards.products.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootTest
public class ProductManagerServiceTest {

    @Autowired
    ProductService productService;
    @Test
    public void testAddAndGetProduct(){
        Product product = new Product("123", "test");
        productService.addProduct(product);
        Product result = productService.getProduct("123");
        Assertions.assertEquals(product,result);

    }

    @Test
    public void testAddAndGetProducts(){
        Collection<Product> products = new ArrayList<>();
        products.add(new Product("123", "product1"));
        products.add(new Product("124", "product2"));
        products.add(new Product("125", "product3"));
        products.add(new Product("126", "product3"));
        products.add(new Product("127", "product4"));
        products.stream().forEach(productService::addProduct);
        Collection<Product> result = productService.getAllProducts();
        Assertions.assertEquals(products,result);

    }
}
