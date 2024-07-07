package com.gvgroup.ordermanagement.service;

public interface MessagePublisherService {

    void publish(String destination, Object message);

}
