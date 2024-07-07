package com.gvgroup.ordermanagement.value;

import lombok.Value;

import java.util.UUID;

@Value
public class UserId {

    private UUID id;

    public static UserId create() {
        return new UserId(UUID.randomUUID());
    }

    public static UserId from(String userId) {
        return from(UUID.fromString(userId));
    }

    public static UserId from(UUID userId) {
        return new UserId(userId);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
