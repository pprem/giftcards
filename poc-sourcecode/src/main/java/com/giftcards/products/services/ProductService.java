package com.giftcards.products.services;


import com.giftcards.products.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private Map<String,Product> products;

    public ProductService(){
        products = new HashMap<>();
    }

    public Product getProduct(String id) {
        return products.get(id);
    }

    public List<Product> getAllProducts() {
       return new ArrayList<>(products.values());

    }

    public Product addProduct(Product product) {
        products.put(product.getId(),product);
        return product;
    }
}
