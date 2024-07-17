package com.microservices.customer.exception;

public class ValidationException extends  RuntimeException {

    private String typeId;

    public ValidationException(String message, String typeId) {
        super(message);
        this.typeId = typeId;
    }

    public String getTyepeId() {
        return typeId;
    }
    
}
