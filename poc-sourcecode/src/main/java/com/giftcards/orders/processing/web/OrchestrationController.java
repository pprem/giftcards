package com.giftcards.orders.processing.web;


import com.giftcards.orders.model.GiftCardOrder;
import com.giftcards.orders.processing.OrchestrationService;
import com.giftcards.orders.processing.OrderStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/api")
@Api(value = "Orchestration REST API", tags ={"3. Orchestration REST APIs"} )
public class OrchestrationController {
    @Autowired
    OrchestrationService orderStateMachineService;
    @ResponseBody
    @PostMapping("/orders/orchestrationengine/")
    @ApiOperation(value = "Submit order to Orchestration engine",
            notes = "Provide the order details to be submitted to Order Orchestration Engine",
            response = OrderStatus.class)
    public ResponseEntity<OrderStatus> processOrder(@RequestBody GiftCardOrder order){
        OrderStatus orderStatus = orderStateMachineService.processOrder(order);
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderStatus.getOrderId())
                .toUri();
        ResponseEntity<OrderStatus> result = ResponseEntity.created(location).body(orderStatus);

        return result;
    }

    @ResponseBody
    @GetMapping("/orders/orchestrationengine/status/{orderid}/{lineitemid}")
    @ApiOperation(value = "Retrieve the status for given order and/or lineitem under the order",
            notes = "Provide the order id and lineitemid to retrieve the order status",
            response = OrderStatus.class)
    public ResponseEntity<OrderStatus> getStatus( @PathVariable String orderid,@PathVariable(required = false) String lineitemid){
        OrderStatus orderStatus = orderStateMachineService.getOrderStatus(orderid,lineitemid);

        ResponseEntity<OrderStatus> result = new ResponseEntity(orderStatus, HttpStatus.OK);

        return result;
    }
}
