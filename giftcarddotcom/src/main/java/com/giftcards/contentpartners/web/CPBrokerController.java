package com.giftcards.contentpartners.web;

import com.giftcards.contentpartners.model.ContentPartner;
import com.giftcards.contentpartners.service.CPBrokerService;
import com.giftcards.orders.model.GiftCardOrder;
import com.giftcards.products.models.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/api")
@Api(value = "Content Partners Broker REST API", tags ={"Content Partners Broker REST API"} )
public class CPBrokerController {
    @Autowired
    CPBrokerService cpBrokerService;


    RestTemplate restTemplate =new RestTemplate();;

    @ResponseBody
    @PostMapping("/cpproxy/register")
    @ApiOperation(value = "Registers new content partners with Broker Service",
            notes = "Provide the details for content partner to register with Broker",
            response = ContentPartner.class)
    public  ResponseEntity<ContentPartner>  registerVendor(@RequestBody ContentPartner contentPartner){
        ContentPartner result =  cpBrokerService.registerVendor(contentPartner);
        ResponseEntity<ContentPartner> response = new ResponseEntity<>(result, HttpStatus.OK);
        return  response;

    }

    @ResponseBody
    @PostMapping("/cpproxy/order/{contentpartnerid}")
    @ApiOperation(value = "Submit order to specific content partner in the path variable of the URL",
            notes = "Provide the details for content partner to register with Broker",
            response = ContentPartner.class)
    public  ResponseEntity<GiftCardOrder>  submitOrder(@PathVariable String contentpartnerid, @RequestBody GiftCardOrder order){
        ContentPartner result =  cpBrokerService.getVendor(contentpartnerid);
        ResponseEntity<GiftCardOrder> submitVisaResponse = restTemplate.postForEntity(result.getUrl().toString(),order,GiftCardOrder.class);
        ResponseEntity<GiftCardOrder> response = new ResponseEntity<>(order, HttpStatus.OK);
        return  response;

    }
}
