package com.apps.quantitymeasurement.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e, HttpServletRequest req) {
        String msg = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return build(HttpStatus.BAD_REQUEST, "Quantity Measurement Error", msg, req);
    }

    @ExceptionHandler({CategoryMismatchException.class, QuantityMeasurementException.class, InvalidUnitException.class})
    public ResponseEntity<ErrorResponse> handleBusinessLogic(RuntimeException e, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, "Quantity Measurement Error", e.getMessage(), req);
    }

    private ResponseEntity<ErrorResponse> build(HttpStatus status, String err, String msg, HttpServletRequest req) {
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(), status.value(), err, msg, req.getRequestURI());
        return new ResponseEntity<>(res, status);
    }
}