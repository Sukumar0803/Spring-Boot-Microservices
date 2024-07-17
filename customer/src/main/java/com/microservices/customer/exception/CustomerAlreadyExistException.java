package com.microservices.customer.exception;

public class CustomerAlreadyExistException extends RuntimeException {

    private String typeId;

    public CustomerAlreadyExistException(String message, String typeId) {
        super(message);
        this.typeId = typeId;
    }

    public String getTypeId() {
        return typeId;
    }

}
