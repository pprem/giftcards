package com.giftcards.orders.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class GiftCardOrder {
    String id;
    List<LineItem> lineItems;
    BigDecimal totalAmount;

    public GiftCardOrder(String orderid){
        this.id = orderid;
    }

}
