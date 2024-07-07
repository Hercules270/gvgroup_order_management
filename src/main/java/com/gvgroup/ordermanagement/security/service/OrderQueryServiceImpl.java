package com.gvgroup.ordermanagement.security.service;

import com.gvgroup.ordermanagement.entity.Order;
import com.gvgroup.ordermanagement.exception.ErrorCode;
import com.gvgroup.ordermanagement.exception.OrderNotFoundException;
import com.gvgroup.ordermanagement.repository.OrderRepository;
import com.gvgroup.ordermanagement.value.OrderId;
import com.gvgroup.ordermanagement.value.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService {

    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> findOrderByOrderIdAndUserIdNullable(OrderId orderId, UserId userId) {
        return orderRepository.findByOrderIdAndUserId(orderId.getId(), userId.getId());
    }

    @Override
    public Order findOrderByOrderId(OrderId orderId) {
        return orderRepository.findByOrderId(orderId.getId())
                .orElseThrow(() -> new OrderNotFoundException("Order with order id " + orderId + " does not exist",
                        "Order with order id " + orderId + " does not exist",
                        ErrorCode.ORDER_WITH_ORDER_ID_NOT_FOUND));

    }

    @Override
    public Page<Order> findAllOrdersByUserId(UserId userId, int page, int size) {
        return orderRepository.findAllByUserId(userId.getId(), PageRequest.of(page, size));

    }
}
