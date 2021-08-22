package com.giftcards.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LineItem {
    private String itemid;
    private BigDecimal unitPrice;
    private int qty;
    private BigDecimal totalPrice;

    public LineItem(String itemid,BigDecimal unitPrice, int qty){
        this.itemid = itemid;
        this.unitPrice = unitPrice;
        this.qty = qty;
        totalPrice = unitPrice.multiply(BigDecimal.valueOf(qty));
    }

}
