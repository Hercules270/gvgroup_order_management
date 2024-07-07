package com.gvgroup.ordermanagement.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    GENERAL_ERROR(-1, HttpStatus.BAD_REQUEST),
    ORDER_WITH_ORDER_ID_NOT_FOUND(-2, HttpStatus.NOT_FOUND);

    private final int code;
    private final HttpStatus httpStatus;

    ErrorCode(int code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
