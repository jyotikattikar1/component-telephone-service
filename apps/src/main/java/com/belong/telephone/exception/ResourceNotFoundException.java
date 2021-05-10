package com.belong.telephone.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2387081629764316147L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
