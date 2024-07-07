package com.gvgroup.ordermanagement.security.config;

import com.gvgroup.ordermanagement.exception.ErrorCode;
import com.gvgroup.ordermanagement.exception.OrderAccessDeniedException;
import com.gvgroup.ordermanagement.security.service.OrderQueryService;
import com.gvgroup.ordermanagement.value.OrderId;
import com.gvgroup.ordermanagement.value.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import static com.gvgroup.ordermanagement.security.config.SecurityConstants.USER_ID_CLAIM_NAME;


@Component
@RequiredArgsConstructor
public class OrderBelongPermissionEvaluator implements PermissionEvaluator {

    private final OrderQueryService orderQUeryService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return true;
    }

    public boolean hasCustomPermission(Authentication authentication, String orderId) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        orderQUeryService.findOrderByOrderIdAndUserIdNullable(OrderId.from(orderId), UserId.from(jwt.getClaimAsString(USER_ID_CLAIM_NAME)))
                .orElseThrow(() -> new OrderAccessDeniedException("Order with order id " + orderId.toString() + " does not exist",
                        "Order with order id " + orderId + " does not exist",
                        ErrorCode.ORDER_WITH_ORDER_ID_NOT_FOUND));
        return true;
    }
}
