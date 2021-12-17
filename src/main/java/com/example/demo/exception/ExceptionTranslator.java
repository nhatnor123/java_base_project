package com.example.demo.exception;

import com.example.demo.dto.ResponseObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.enums.ResponseStatus.*;

@ControllerAdvice
public class ExceptionTranslator {
    private static final Logger LOGGER = LogManager.getLogger(ExceptionTranslator.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ResponseObject<?> apiExceptionHandle(ApiException e) {
        LOGGER.error(e.getMessage(), e);
        return new ResponseObject<>(false, e.getErrorStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseObject<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ResponseObject<?> res = new ResponseObject<>();
        res.setSuccess(false);
        res.setCode(REQUEST_BODY_INVALID.getCode());
        res.setMessage(REQUEST_BODY_INVALID.getMessage());
        res.setErrors(errors);

        return new ResponseObject<>(false, REQUEST_BODY_INVALID, errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ResponseObject<String> unhandledError(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return new ResponseObject<>(false, UNHANDLED_ERROR, e.getMessage());
    }

}
