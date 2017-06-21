package com.dummy.universalshop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

/**
 * Created by Mateusz on 16.05.2017.
 */
@ControllerAdvice
@Order
public class DefaultRestResponseEntityExpectionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private LocalizedMessageFacade localizedMessageFacade;

    @ExceptionHandler(value = Exception.class)
    public
    @ResponseBody
    ResponseEntity<Object> handleException(Exception ex, WebRequest request, Locale locale) {
        String message = localizedMessageFacade.getValue("InternalServerError", locale);
        return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
