package com.giftcards.orders.processing;


import com.giftcards.workflows.services.WorkflowInstanceService;
import com.giftcards.orders.model.GiftCardOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrchestrationService {
    Map<String, String> orderTypeWorkflowDefMap = new HashMap<>();
    @Autowired
    WorkflowInstanceService workflowInstanceService;
    Map<String, OrderStatus> orderStatusHashMap = new HashMap<>();

    public void init(){
        orderTypeWorkflowDefMap.put("item1","wf1");
    }
    public OrderStatus processOrder(GiftCardOrder order) {
        OrderStatus orderStatus =new OrderStatus(order.getId());
        order.getLineItems().stream().forEach(lineitem->{
                String workflowDefId =orderTypeWorkflowDefMap.get(lineitem);
                Map<String,Object> input = new HashMap<>();
                input.put("lineitem",lineitem);
                workflowInstanceService.startWorkflowInstance(workflowDefId,input);
        });

        return orderStatus;
    }

    public OrderStatus updateOrderStatus(String orderid, String lineitemid, String status) {

        OrderStatus orderStatus = orderStatusHashMap.containsKey(orderid)?orderStatusHashMap.get(orderid): new OrderStatus(orderid);
        orderStatus.addLineItemStatusUpdate(lineitemid,status);
        orderStatusHashMap.put(orderStatus.getOrderId(),orderStatus);
        return orderStatus;
    }

    public OrderStatus updateOrderStatus(String orderid, String status) {

        OrderStatus orderStatus = orderStatusHashMap.containsKey(orderid)?orderStatusHashMap.get(orderid): new OrderStatus(orderid);
        orderStatus.addStatusUpdate(status);
        orderStatusHashMap.put(orderStatus.getOrderId(),orderStatus);
        return orderStatus;
    }


    public OrderStatus getOrderStatus(String orderid ) {
        OrderStatus orderStatus = orderStatusHashMap.containsKey(orderid)?orderStatusHashMap.get(orderid):null;
        return orderStatus;
    }

    public OrderStatus getOrderStatus(String orderid, String lineitemid ) {
        OrderStatus orderStatus = orderStatusHashMap.containsKey(orderid)?orderStatusHashMap.get(orderid):null;
        OrderStatus newOrderStatus = new OrderStatus(orderStatus.getOrderId(),orderStatus.getStatus());
        Map<String ,List<String>> lineStatusMap = new HashMap<>();
        lineStatusMap.put(lineitemid,orderStatus.lineitemstatus.get(lineitemid));
        newOrderStatus.setLineitemstatus(lineStatusMap);

        return newOrderStatus;
    }
}
