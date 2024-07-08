package com.gvgroup.ordermanagement.listener;


import com.gvgroup.ordermanagement.model.message.OrderCreatedMessage;
import com.gvgroup.ordermanagement.model.message.OrderDeletedMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.gvgroup.ordermanagement.utils.KafkaConstants.ORDER_CREATED_TOPIC_NAME;
import static com.gvgroup.ordermanagement.utils.KafkaConstants.ORDER_DELETED_TOPIC_NAME;

@Slf4j
//@Component
public class KafkaQueueListener {

    @KafkaListener(topics = ORDER_CREATED_TOPIC_NAME)
    public void orderCreated(OrderCreatedMessage message) {
        log.info("Order created message received {}", message);
    }


    @KafkaListener(topics = ORDER_DELETED_TOPIC_NAME)
    public void orderDeleted(OrderDeletedMessage message) {
        log.info("Order deleted message received {}", message);
    }
}
