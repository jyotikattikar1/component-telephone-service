package com.belong.telephone.exception;

import java.util.List;


public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -4367181649866316234L;

    private List<Error> details;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, List<Error> details) {
        super(message);
        this.details = details;
    }

    public List<Error> getDetails() {
        return details;
    }

}
