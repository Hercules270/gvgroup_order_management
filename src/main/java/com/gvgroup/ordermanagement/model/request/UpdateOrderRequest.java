package com.gvgroup.ordermanagement.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateOrderRequest {

    private BigDecimal price;

    private String product;

    private Integer quantity;
}
