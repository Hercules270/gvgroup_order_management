package com.gvgroup.ordermanagement.config;


import com.gvgroup.ordermanagement.entity.Order;
import com.gvgroup.ordermanagement.utils.HazelcastOrderIdSerializer;
import com.gvgroup.ordermanagement.utils.HazelcastOrderSerializer;
import com.gvgroup.ordermanagement.value.OrderId;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    public static final String ORDERS = "orders";

    private final HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(createConfig());
    @Bean
    public HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance(createConfig());
    }

    public Config createConfig() {
        Config config = new Config();
        config.addMapConfig(mapConfig());
        config.getSerializationConfig().addSerializerConfig(orderSerializerConfig());
        config.getSerializationConfig().addSerializerConfig(orderIdSerializerConfig());
        return config;
    }

    private SerializerConfig orderSerializerConfig() {
        return new SerializerConfig().setImplementation(new HazelcastOrderSerializer()).setTypeClass(Order.class);
    }

    private SerializerConfig orderIdSerializerConfig() {
        return new SerializerConfig().setImplementation(new HazelcastOrderIdSerializer()).setTypeClass(OrderId.class);
    }

    private MapConfig mapConfig() {
        MapConfig mapConfig = new MapConfig(ORDERS);
        mapConfig.setTimeToLiveSeconds(360);
        mapConfig.setMaxIdleSeconds(400);
        return mapConfig;
    }


}
