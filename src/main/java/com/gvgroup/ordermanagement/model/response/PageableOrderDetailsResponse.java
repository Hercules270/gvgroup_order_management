package com.gvgroup.ordermanagement.model.response;

import com.gvgroup.ordermanagement.entity.Order;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@SuperBuilder
public class PageableOrderDetailsResponse extends AbstractPageableResponse<PageableOrderDetailsResponse.CompactOrderDetails> {


    public static PageableOrderDetailsResponse toJson(Page<Order> orders) {
        return PageableOrderDetailsResponse.builder()
                .totalPages(orders.getTotalPages())
                .totalElements(orders.getTotalElements())
                .elements(orders.getContent().stream()
                        .map(CompactOrderDetails::toJson)
                        .toList())
                .build();
    }

    @Data
    @Builder
    public static class CompactOrderDetails {

        private String orderId;
        private String userId;
        private BigDecimal totalAmount;
        private BigDecimal price;
        private String product;

        public static CompactOrderDetails toJson(Order order) {
            return CompactOrderDetails.builder()
                    .orderId(order.getOrderId().toString())
                    .userId(order.getUserId().toString())
                    .totalAmount(order.getTotalAmount())
                    .price(order.getPrice())
                    .product(order.getProduct())
                    .build();
        }
    }

}
