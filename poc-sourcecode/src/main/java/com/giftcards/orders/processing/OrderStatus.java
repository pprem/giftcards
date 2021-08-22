package com.giftcards.orders.processing;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@EqualsAndHashCode
@ToString
@Data
public class OrderStatus {

    private String orderId;


    Map<String , List<String>> lineitemstatus;

    List<String> status;

    public OrderStatus(){
        lineitemstatus  = new HashMap<>();
        status = new ArrayList<>();
    }

    public OrderStatus(String orderid) {
        this.orderId = orderid;
        lineitemstatus  = new HashMap<>();
        status = new ArrayList<>();
    }


    public OrderStatus(String orderid,List<String> status) {
        this.orderId = orderid;
        lineitemstatus  = new HashMap<>();
        this.status = new ArrayList<>(status);

    }

    public void addLineItemStatusUpdate(String lineitemid, String statusupdate) {
        List<String> statusList = lineitemstatus.containsKey(lineitemid)?lineitemstatus.get(lineitemid): new ArrayList<>();
        statusList.add(statusupdate);
        lineitemstatus.put(lineitemid,statusList);

    }

    public void addStatusUpdate(String statusupdate) {
        status.add(statusupdate);
    }


}
