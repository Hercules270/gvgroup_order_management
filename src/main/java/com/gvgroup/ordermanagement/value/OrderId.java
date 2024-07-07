package com.gvgroup.ordermanagement.value;


import lombok.Value;

import java.util.UUID;

@Value
public class OrderId {

    private UUID id;

    public static OrderId create() {
        return new OrderId(UUID.randomUUID());
    }

    public static OrderId from(String OrderId) {
        return from(UUID.fromString(OrderId));
    }

    public static OrderId from(UUID OrderId) {
        return new OrderId(OrderId);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}