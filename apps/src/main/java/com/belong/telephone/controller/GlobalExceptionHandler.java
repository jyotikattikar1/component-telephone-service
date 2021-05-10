package com.belong.telephone.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.belong.common.APIResponse;
import com.belong.common.Error;
import com.belong.common.ErrorCode;
import com.belong.common.MessageByLocale;
import com.belong.telephone.exception.ResourceNotFoundException;

/**
 * <h1>Global Exception Handler</h1> Handles general exceptions in all services
 * * in telephone processor service.
 */
@ControllerAdvice
@EnableWebMvc
@RestController
public class GlobalExceptionHandler {

	
   // private MessageByLocale messageByLocale;
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

//    @Autowired
//    public GlobalExceptionHandler(MessageByLocale messageByLocale) {
//      //  this.messageByLocale = messageByLocale;
//    }

    @ExceptionHandler
    public ResponseEntity<APIResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException exception) {
        APIResponse responseBody = new APIResponse();
        Error error = new Error(ErrorCode.METHOD_NOT_ALLOWED.name(), "Method not allowed");
        responseBody.setStatus(ErrorCode.METHOD_NOT_ALLOWED.value());
        responseBody.setError(error);

        return ResponseEntity.status(ErrorCode.METHOD_NOT_ALLOWED.statusCode()).body(responseBody);
    }


    @ExceptionHandler
    public ResponseEntity<APIResponse> handleUnsupportedMediaType(HttpMediaTypeNotSupportedException exception) {
        APIResponse responseBody = new APIResponse();
        Error error = new Error(ErrorCode.UNSUPPORTED_MEDIA_TYPE.name(),
                "Unsupportable media type");
        responseBody.setStatus(ErrorCode.UNSUPPORTED_MEDIA_TYPE.value());
        responseBody.setError(error);
        return ResponseEntity.status(ErrorCode.UNSUPPORTED_MEDIA_TYPE.statusCode()).body(responseBody);
    }

    @ExceptionHandler
    public ResponseEntity<APIResponse> handleGeneralException(Exception exception) {
        APIResponse responseBody = new APIResponse();
        Error error = new Error(ErrorCode.INTERNAL_SERVER_ERROR.name(),getErrorMessage("error-message.internal-server-error"));
        responseBody.setStatus(ErrorCode.INTERNAL_SERVER_ERROR.value());
        responseBody.setError(error);
        LOG.error("Internal Server Error", exception);
        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.statusCode()).body(responseBody);
    }

    @ExceptionHandler
    public ResponseEntity<APIResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException exception) {
        // handle invalid data types in path variables etc.
        APIResponse responseBody = new APIResponse();
        Error error = new Error(ErrorCode.BAD_REQUEST.name(),"Bad Request");
        List<Error> errorDetails = new ArrayList<>();
        String[] errorArgs = { exception.getName() };
        errorDetails.add(new Error(ErrorCode.INVALID_ARGUMENT.name(), exception.getName(),
               "In valid argument"));
        error.setDetails(errorDetails);
        responseBody.setStatus(ErrorCode.BAD_REQUEST.value());
        responseBody.setError(error);
        return ResponseEntity.status(ErrorCode.BAD_REQUEST.statusCode()).body(responseBody);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<APIResponse> handleRouteNotFound(NoHandlerFoundException exception) {
        APIResponse responseBody = new APIResponse();
        Error error = new Error(ErrorCode.NOT_FOUND.name(), getErrorMessage("error-message.not-found.invalid-route"));
        responseBody.setStatus(ErrorCode.NOT_FOUND.value());
        responseBody.setError(error);
        return ResponseEntity.status(ErrorCode.NOT_FOUND.statusCode()).body(responseBody);
    }

 
    @ExceptionHandler
    public ResponseEntity<APIResponse> handleResourceNotFoundException(
            ResourceNotFoundException resourceNotFoundException) {
        APIResponse responseBody = new APIResponse();
        Error error = new Error(ErrorCode.NOT_FOUND.name(), resourceNotFoundException.getMessage());
        responseBody.setStatus(ErrorCode.NOT_FOUND.value());
        responseBody.setError(error);
        return ResponseEntity.status(ErrorCode.NOT_FOUND.statusCode()).body(responseBody);
    }

 
    private String getErrorMessage(String errorKey) {
        return "test";
    }
  
}