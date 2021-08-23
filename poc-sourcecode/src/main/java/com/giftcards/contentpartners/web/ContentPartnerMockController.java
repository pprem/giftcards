package com.giftcards.contentpartners.web;

import com.giftcards.contentpartners.model.ContentPartner;
import com.giftcards.orders.model.GiftCardOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
@Api(value = "Content Partners Mock REST API ", tags ={"5. Content Partners Mock REST APIs "})
public class ContentPartnerMockController {

    @ResponseBody
    @PostMapping("/contentpartners/visa")
    @ApiOperation(value = "Submit order to content partner - VISA",
            notes = "Provide the order details to be submited to content partner - VISA",
            response = GiftCardOrder.class)
    public ResponseEntity<GiftCardOrder> submitOrderRequestVISA(@RequestBody GiftCardOrder order){
        ResponseEntity<GiftCardOrder> response = new ResponseEntity<>(order, HttpStatus.OK);
        return  response;

    }
    @ResponseBody
    @PostMapping("/contentpartners/grubhub")
    @ApiOperation(value = "Submit order to content partner - GRUBHUB",
            notes = "Provide the order details to be submited to content partner - VISA",
            response = GiftCardOrder.class)
    public  ResponseEntity<GiftCardOrder>  submitOrderRequestGRUBHUB(@RequestBody GiftCardOrder order){

        ResponseEntity<GiftCardOrder> response = new ResponseEntity<>(order, HttpStatus.OK);
        return  response;
    }
    @ResponseBody
    @PostMapping("/contentpartners/ulta")
    @ApiOperation(value = "Submit order to content partner - ULTA",
            notes = "Provide the order details to be submited to content partner - VISA",
            response = GiftCardOrder.class)
    public  ResponseEntity<GiftCardOrder>  submitOrderRequestULTA(@RequestBody GiftCardOrder order){

        ResponseEntity<GiftCardOrder> response = new ResponseEntity<>(order, HttpStatus.OK);
        return  response;
    }
    @ResponseBody
    @PostMapping("/contentpartners/hoteldotcom")
    @ApiOperation(value = "Submit order to content partner - HOTELDOTCOM",
            notes = "Provide the order details to be submited to content partner - HOTELDOTCOM",
            response = GiftCardOrder.class)
    public  ResponseEntity<GiftCardOrder>  submitOrderRequestHOTELDOTCOM(@RequestBody GiftCardOrder order){

        ResponseEntity<GiftCardOrder> response = new ResponseEntity<>(order, HttpStatus.OK);
        return  response;
    }
}
