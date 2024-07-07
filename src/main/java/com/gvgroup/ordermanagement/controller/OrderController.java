package com.gvgroup.ordermanagement.controller;


import com.gvgroup.ordermanagement.facade.OrderFacade;
import com.gvgroup.ordermanagement.model.request.CreateOrderRequest;
import com.gvgroup.ordermanagement.model.request.UpdateOrderRequest;
import com.gvgroup.ordermanagement.model.response.ErrorResponse;
import com.gvgroup.ordermanagement.model.response.OrderDetailsResponse;
import com.gvgroup.ordermanagement.model.response.PageableOrderDetailsResponse;
import com.gvgroup.ordermanagement.value.OrderId;
import com.gvgroup.ordermanagement.value.UserId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.gvgroup.ordermanagement.security.config.SecurityConstants.USER_ID_CLAIM_NAME;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDetailsResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @Operation(summary = "Create order", description = "Create order")
    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping
    public ResponseEntity<OrderDetailsResponse> createOrder(@RequestBody @Valid CreateOrderRequest orderRequest,
                                                            @AuthenticationPrincipal Jwt authentication) {
        return orderFacade.createOrder(getUserId(authentication), orderRequest);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDetailsResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @Operation(summary = "Order Details", description = "Get details of order")
    @PreAuthorize("isAuthenticated() && @orderBelongPermissionEvaluator.hasCustomPermission(authentication, #orderId)")
    @GetMapping("/{order_id}")
    public ResponseEntity<OrderDetailsResponse> getOrderDetails(@PathVariable("order_id") String orderId,
                                                                @AuthenticationPrincipal Jwt authentication) {
        return orderFacade.getOrderDetails(OrderId.from(orderId));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PageableOrderDetailsResponse.class))})})
    @Operation(summary = "Orders", description = "Get list of orders using pagination")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public ResponseEntity<PageableOrderDetailsResponse> getOrders(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size,
                                                                  @AuthenticationPrincipal Jwt authentication) {
        return orderFacade.getOrders(getUserId(authentication), page, size);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDetailsResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @PreAuthorize("isAuthenticated() && @orderBelongPermissionEvaluator.hasCustomPermission(authentication, #orderId)")
    @PutMapping("/{order_id}")
    public ResponseEntity<OrderDetailsResponse> updateOrder(@PathVariable("order_id") String orderId,
                                                            @RequestBody UpdateOrderRequest updateOrderRequest) {
        return orderFacade.updateOrder(OrderId.from(orderId), updateOrderRequest);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order deleted",
                    content = {@Content}),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @Operation(summary = "Delete orders", description = "Delete orders")
    @PreAuthorize("isAuthenticated() && @orderBelongPermissionEvaluator.hasCustomPermission(authentication, #orderId)")
    @DeleteMapping("/{order_id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("order_id") String orderId) {
        return orderFacade.deleteOrder(OrderId.from(orderId));
    }

    private UserId getUserId(Jwt jwt) {
        return UserId.from(jwt.getClaimAsString(USER_ID_CLAIM_NAME));
    }
}
