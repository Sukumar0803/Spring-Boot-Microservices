package com.microservices.customer.utils;

public class Constants {
    private Constants() {}

    public static class SystemErrorCode {
        public static final String SYSTEM_EXCEPTION_ERROR_CODE = "T001";
        public static final String VALIDATION_EXCEPTION_ERROR_CODE = "B001";
        public static final String CUSTOMER_NOT_FOUND_EXCEPTION_ERROR_CODE = "B003";
        public static final String CUSTOMER_ALREADY_EXISTS_EXCEPTION_ERROR_CODE = "B004";
        public static final String ILLEGAL_METHOD_ARGUMENTS_EXCEPTION_ERROR_CODE = "B005";
    }

    public static class TypeErrorCode {
        public static final String INVALID_METHOD_ARGUMENTS = "INVALID_METHOD_ARGUMENTS";
        public static final String TECHNICAL_ERROR_TYPE_ID = "TECHNICAL_ERROR";
    }

    public static class ErrorMessage {
        public static final String CUSTOMER_ALREADY_EXISTS_ERROR_MESSAGE = "Customer already exist with email: %s";
        public static final String TECHNICAL_ERROR_MESSAGE = "Please Contact the ApplicationSupport";
    }

}
