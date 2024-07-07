package com.gvgroup.ordermanagement.utils;

import com.gvgroup.ordermanagement.entity.Order;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

public class HazelcastOrderSerializer implements StreamSerializer<Order> {


    @Override
    public void write(ObjectDataOutput out, Order order) throws IOException {
        out.writeLong(order.getId());
        out.writeString(getValue(order.getOrderId(), UUID::toString));
        out.writeString(getValue(order.getUserId(), UUID::toString));
        out.writeString(order.getStatus());
        out.writeString(getValue(order.getCreateDate(), Instant::toString));
        out.writeString(getValue(order.getUpdateDate(), Instant::toString));
        out.writeDouble(getValue(order.getPrice(), BigDecimal::doubleValue));
        out.writeString(order.getProduct());
        out.writeInt(order.getQuantity());
        out.writeDouble(getValue(order.getTotalAmount(), BigDecimal::doubleValue));
    }


    private <T, R> R getValue(T object, Function<T, R> function) {
        if(Objects.nonNull(object)) {
            return function.apply(object);
        }
        return null;
    }

    @Override
    public Order read(ObjectDataInput in) throws IOException {
        return Order.builder()
                .id(in.readLong())
                .orderId(getValue(in.readString(), UUID::fromString))
                .userId(getValue(in.readString(), UUID::fromString))
                .status(in.readString())
                .createDate(getValue(in.readString(), Instant::parse))
                .updateDate(getValue(in.readString(), Instant::parse))
                .price(getValue(in.readDouble(), BigDecimal::valueOf))
                .product(in.readString())
                .quantity(in.readInt())
                .totalAmount(getValue(in.readDouble(), BigDecimal::valueOf))
                .build();
    }

    @Override
    public int getTypeId() {
        return 2;
    }

}
