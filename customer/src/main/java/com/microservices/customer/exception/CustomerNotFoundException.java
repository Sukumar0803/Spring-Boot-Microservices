package com.microservices.customer.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class CustomerNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 8464635909037126866L;

    private final String typeId;

    public CustomerNotFoundException(String message, String typeId) {
        super(message);
        this.typeId = typeId;
    }

}
