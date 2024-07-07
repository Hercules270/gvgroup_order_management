package com.gvgroup.ordermanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class KafkaMessagePublisherService implements MessagePublisherService {

    private final KafkaTemplate kafkaTemplate;

    @Override
    public void publish(String destination, Object message) {
        kafkaTemplate.send(destination, message);
    }
}
