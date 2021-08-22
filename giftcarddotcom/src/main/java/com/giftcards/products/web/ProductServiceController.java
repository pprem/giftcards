package com.giftcards.products.web;


import com.giftcards.products.models.Product;
import com.giftcards.products.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api")
@Api(value = "Product Service REST API", tags ={"Product Service REST APIs"} )
public class ProductServiceController {


    @Autowired
    ProductService productManagerService;

    public ProductServiceController(){

    }


    @ResponseBody
    @PostMapping("/products/")
    @ApiOperation(value = "Adds a new Product",
            notes = "Provide product details to create new product in the catalog",
            response = Product.class)
    public ResponseEntity<Product> addProduct(@RequestBody Product product){

        Product p = productManagerService.addProduct(product);
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
         ResponseEntity<Product> result = ResponseEntity.created(location).body(p);

        return result;
    }

    @ResponseBody
    @GetMapping("/products/{id}")
    @ApiOperation(value = "Finds Product by id",
    notes = "Provide an id to look up specific product in the catalog",
    response = Product.class)
    public ResponseEntity<Product> getProduct(@ApiParam(value = "ID value for the product you need to retrieve", required = true) @PathVariable String id){
        Product product = productManagerService.getProduct(id);
        ResponseEntity<Product> responseEntity = new ResponseEntity(product, HttpStatus.OK);
        return responseEntity;
    }

    @ResponseBody
    @GetMapping("/products/")
    @ApiOperation(value = "Retrieves All the products in the catalog",
            notes = "Use this service to retrieve all the products in the catalog",
            response = Product.class, responseContainer = "List")
    public ResponseEntity< List<Product>> getProducts(){
        List<Product> products = productManagerService.getAllProducts();
        ResponseEntity<List<Product>> responseEntity = new ResponseEntity(products, HttpStatus.OK);
        return responseEntity;
    }

}
