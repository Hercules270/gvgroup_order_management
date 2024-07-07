package com.gvgroup.ordermanagement.model.message;


import com.gvgroup.ordermanagement.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedMessage {

    private UUID orderId;
    private UUID userId;
    private BigDecimal totalAmount;
    private String product;


    public static OrderCreatedMessage convert(Order order) {
        return OrderCreatedMessage.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .totalAmount(order.getTotalAmount())
                .product(order.getProduct())
                .build();
    }
}
