package com.gvgroup.ordermanagement.service;


import com.gvgroup.ordermanagement.entity.Order;
import com.gvgroup.ordermanagement.enums.OrderStatus;
import com.gvgroup.ordermanagement.repository.OrderRepository;
import com.gvgroup.ordermanagement.security.service.OrderQueryService;
import com.gvgroup.ordermanagement.value.OrderId;
import com.gvgroup.ordermanagement.value.UserId;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderQueryService orderQueryService;

    @Override
    public Order createOrder(OrderId orderId, UserId userId, String product, Integer quantity, BigDecimal price) {
        Order order = Order.builder()
                .orderId(orderId.getId())
                .userId(userId.getId())
                .status(OrderStatus.created.name())
                .createDate(Instant.now())
                .product(product)
                .quantity(quantity)
                .price(price)
                .totalAmount(price.multiply(BigDecimal.valueOf(quantity)))
                .build();
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(OrderId orderId, String product, BigDecimal price, Integer quantity) {
        Order order = orderQueryService.findOrderByOrderId(orderId);
        if(StringUtils.isNotBlank(product)) {
            order.setProduct(product);
        }
        if(!Objects.isNull(price)) {
            order.setPrice(price);
        }
        if(!Objects.isNull(quantity)) {
            order.setQuantity(quantity);
        }
        order.setTotalAmount(order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void deleteOrder(OrderId orderId) {
        orderRepository.deleteByOrderId(orderId.getId());
    }
}
