package com.gvgroup.ordermanagement.exception;


import com.gvgroup.ordermanagement.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.gvgroup.ordermanagement.exception.ErrorCode.GENERAL_ERROR;


@Slf4j
@RestControllerAdvice
public class ServiceExceptionHandler {


    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundExceptionHandler(OrderNotFoundException ex) {
        log.error("Order not found exception", ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getResponseMessage(), ex.getErrorCode().getCode()), ex.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> baseExceptionHandler(BaseException ex) {
        log.error("Base exception happened", ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getResponseMessage(), ex.getErrorCode().getCode()), ex.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(OrderAccessDeniedException.class)
    public ResponseEntity<ErrorResponse> orderAccessDeniedExceptionHandler(OrderAccessDeniedException ex) {
        log.error("Order access denied exception happened", ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getResponseMessage(), ex.getErrorCode().getCode()), ex.getErrorCode().getHttpStatus());
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Void> accessDeniedExceptionHandler(AccessDeniedException ex) {
        log.error("Access Denied Exception", ex);
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        log.error("Method Validation Exception", ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getFieldError().getDefaultMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> userAlreadyExistsExceptionHandler(Exception ex) {
        log.error("Unhandled exception happened", ex);
        return new ResponseEntity<>(new ErrorResponse("General Error", GENERAL_ERROR.getCode()), HttpStatus.BAD_REQUEST);
    }

}
