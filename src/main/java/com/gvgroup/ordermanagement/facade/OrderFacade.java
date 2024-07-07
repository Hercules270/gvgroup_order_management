package com.gvgroup.ordermanagement.facade;


import com.gvgroup.ordermanagement.entity.Order;
import com.gvgroup.ordermanagement.model.request.CreateOrderRequest;
import com.gvgroup.ordermanagement.model.request.UpdateOrderRequest;
import com.gvgroup.ordermanagement.model.response.OrderDetailsResponse;
import com.gvgroup.ordermanagement.model.response.PageableOrderDetailsResponse;
import com.gvgroup.ordermanagement.security.service.OrderQueryService;
import com.gvgroup.ordermanagement.service.OrderService;
import com.gvgroup.ordermanagement.value.OrderId;
import com.gvgroup.ordermanagement.value.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final OrderQueryService orderQueryService;

    public ResponseEntity<OrderDetailsResponse> createOrder(UserId userId, CreateOrderRequest orderRequest) {
        OrderId orderId = OrderId.create();
        Order order = orderService.createOrder(orderId,
                userId,
                orderRequest.getProduct(),
                orderRequest.getQuantity(),
                orderRequest.getPrice());
        return new ResponseEntity<>(OrderDetailsResponse.toJson(order), HttpStatus.CREATED);
    }

    public ResponseEntity<OrderDetailsResponse> getOrderDetails(OrderId orderId) {
        return new ResponseEntity<>(OrderDetailsResponse.toJson(orderQueryService.findOrderByOrderId(orderId)), HttpStatus.OK);
    }

    public ResponseEntity<PageableOrderDetailsResponse> getOrders(UserId userId, int page, int size) {
        Page<Order> orders = orderQueryService.findAllOrdersByUserId(userId, page, size);
        return new ResponseEntity<>(PageableOrderDetailsResponse.toJson(orders), HttpStatus.OK);
    }

    public ResponseEntity<OrderDetailsResponse> updateOrder(OrderId orderId, UpdateOrderRequest updateOrderRequest) {
        Order order = orderService.updateOrder(orderId, updateOrderRequest.getProduct(), updateOrderRequest.getPrice(), updateOrderRequest.getQuantity());
        return new ResponseEntity<>(OrderDetailsResponse.toJson(order), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteOrder(OrderId orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
