package com.giftcards.orders.web;

import com.giftcards.orders.model.GiftCardOrder;
import com.giftcards.orders.processing.OrderStatus;
import com.giftcards.orders.services.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Order Service Rest API", tags ={"2. Order Service REST APIs"} )
public class OrderController {

    @Autowired
    OrderService orderService;


    @ResponseBody
    @PostMapping("/orders")
    @ApiOperation(value = "Creates a new order",
            notes = "Provide the order details to create a new order",
            response = GiftCardOrder.class)
    public ResponseEntity<GiftCardOrder> createOrder(@RequestBody GiftCardOrder order){
        GiftCardOrder resOrdewr = orderService.createOrder(order);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resOrdewr.getId())
                .toUri();
        ResponseEntity<GiftCardOrder> result = ResponseEntity.created(location).body(resOrdewr);

        return result;

    }

    @ResponseBody
    @GetMapping("/orders/{id}")
    @ApiOperation(value = "Find an order by id",
            notes = "Provide an orderid retrieve the order details",
            response = GiftCardOrder.class)
    public ResponseEntity<GiftCardOrder> getOrderById( @PathVariable  String id){

        GiftCardOrder order =  orderService.getOrder(id);
        ResponseEntity<GiftCardOrder> responseEntity = new ResponseEntity<>(order, HttpStatus.OK);

        return responseEntity;


    }




    @ResponseBody
    @GetMapping("/orders/")
    @ApiOperation(value = "Retrieves all the orders",
            notes = "Use this API to retrieve all the orders",
            response = GiftCardOrder.class, responseContainer = "List")
    public ResponseEntity< List<GiftCardOrder> >  getAllOrders( ){

        List<GiftCardOrder> orders =  orderService.getAllOrders();
        ResponseEntity< List<GiftCardOrder> > responseEntity = new ResponseEntity<>(orders, HttpStatus.OK);

        return responseEntity;


    }



}
