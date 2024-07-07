package com.gvgroup.ordermanagement.exception;



public class OrderNotFoundException extends BaseException {

    public OrderNotFoundException(String message, String responseMessage, ErrorCode errorCode) {
        super(message, responseMessage, errorCode);
    }
}