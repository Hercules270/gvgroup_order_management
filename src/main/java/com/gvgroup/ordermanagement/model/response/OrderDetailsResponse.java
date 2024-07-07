package com.gvgroup.ordermanagement.model.response;


import com.gvgroup.ordermanagement.entity.Order;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class OrderDetailsResponse {

    private UUID orderId;

    private UUID userId;

    private String status;

    private Instant createDate;

    private Instant updateDate;

    private BigDecimal price;

    private String product;

    private int quantity;

    private BigDecimal totalAmount;

    public static OrderDetailsResponse toJson(Order order) {
        return OrderDetailsResponse.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .status(order.getStatus())
                .createDate(order.getCreateDate())
                .updateDate(order.getUpdateDate())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .totalAmount(order.getTotalAmount())
                .product(order.getProduct())
                .build();

    }
}
