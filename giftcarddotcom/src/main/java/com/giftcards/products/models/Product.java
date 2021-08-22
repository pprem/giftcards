package com.giftcards.products.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ApiModel(description = "Details about the Product Model ")
public class Product {
    @ApiModelProperty(notes = "The unique id for the product")
    private String id;
    @ApiModelProperty(notes = "The name of the prodcut")
    private String name;
    @ApiModelProperty(notes = "Workflow Definition to process the order")
    private String workflowid;
    @ApiModelProperty(notes = "Information about the cards included in this bundle product")
    private List<CardDetails> cards;
    @ApiModelProperty(notes = "Product Price")
    private BigDecimal price;

    public Product(String id, String name){
        this.id = id;
        this.name = name;
    }
}
