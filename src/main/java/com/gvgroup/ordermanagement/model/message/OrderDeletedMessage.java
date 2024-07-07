package com.gvgroup.ordermanagement.model.message;


import com.gvgroup.ordermanagement.entity.Order;
import com.gvgroup.ordermanagement.value.OrderId;
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
public class OrderDeletedMessage {

    private UUID orderId;

    public static OrderDeletedMessage convert(OrderId orderId) {
        return OrderDeletedMessage.builder()
                .orderId(orderId.getId())
                .build();
    }
}
