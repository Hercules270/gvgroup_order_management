package com.gvgroup.ordermanagement.utils;

import com.gvgroup.ordermanagement.value.OrderId;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;

import java.io.IOException;

public class HazelcastOrderIdSerializer implements StreamSerializer<OrderId> {

    @Override
    public void write(ObjectDataOutput out, OrderId orderId) throws IOException {
        out.writeString(orderId.toString());
    }

    @Override
    public OrderId read(ObjectDataInput in) throws IOException {
        return OrderId.from(in.readString());
    }

    @Override
    public int getTypeId() {
        return 1;
    }
}
