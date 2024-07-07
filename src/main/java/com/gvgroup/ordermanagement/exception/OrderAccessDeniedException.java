package com.gvgroup.ordermanagement.exception;

public class OrderAccessDeniedException extends BaseException{

    public OrderAccessDeniedException(String message, String responseMessage, ErrorCode errorCode) {
        super(message, responseMessage, errorCode);
    }
}
