package com.gvgroup.ordermanagement.service;

import com.gvgroup.ordermanagement.entity.Order;
import com.gvgroup.ordermanagement.value.OrderId;
import com.gvgroup.ordermanagement.value.UserId;

import java.math.BigDecimal;

public interface OrderService {
    Order createOrder(OrderId orderId, UserId userId, String product, Integer quantity, BigDecimal price);

    Order updateOrder(OrderId orderId, String product, BigDecimal price, Integer quantity);

    void deleteOrder(OrderId orderId);
}
