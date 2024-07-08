package com.gvgroup.ordermanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessagePublisherService implements MessagePublisherService {

    private final KafkaTemplate kafkaTemplate;

    @Override
    public void publish(String destination, Object message) {
    log.info("PUBLISHING {}", message);
//        kafkaTemplate.send(destination, message);
    }
}
