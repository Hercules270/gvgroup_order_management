package com.gvgroup.ordermanagement.security.service;

import com.gvgroup.ordermanagement.entity.Order;
import com.gvgroup.ordermanagement.value.OrderId;
import com.gvgroup.ordermanagement.value.UserId;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface OrderQueryService {

    Optional<Order> findOrderByOrderIdAndUserIdNullable(OrderId orderId, UserId userId);

    Order findOrderByOrderId(OrderId orderId);

    Page<Order> findAllOrdersByUserId(UserId userId, int page, int size);
}
