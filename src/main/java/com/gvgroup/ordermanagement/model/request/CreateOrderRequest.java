package com.gvgroup.ordermanagement.model.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateOrderRequest {

    @NotNull(message = "price must not be null.")
    private BigDecimal price;

    @NotNull(message = "product must not be null.")
    private String product;

    @NotNull(message = "quantity must not be null.")
    private Integer quantity;

}
