package com.microservices.customer.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class ApplicationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -9131549667888891847L;

    private final String typeId;

    public ApplicationException(String message, String typeId) {
        super(message);
        this.typeId = typeId;
    }

}
