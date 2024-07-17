package com.microservices.customer.mapper;

import com.microservices.customer.dto.CustomerDTO;
import com.microservices.customer.entity.Customer;

public class CustomerMapper {

    public static Customer toCustomer(CustomerDTO customerDTO) {
        return Customer.builder()
                .id(customerDTO.getId())
                .email(customerDTO.getEmail())
                .firstname(customerDTO.getFirstname())
                .lastname(customerDTO.getLastname())
                .mobileNumber(customerDTO.getMobileNumber())
                .address(AddressMapper.toAddress(customerDTO.getAddress()))
                .build();
    }


    public static CustomerDTO fromCustomer(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .mobileNumber(customer.getMobileNumber())
                .address(AddressMapper.fromAddress(customer.getAddress()))
                .createdBy(customer.getCreatedBy())
                .createdTimestamp(customer.getCreatedTimestamp())
                .modifiedBy(customer.getModifiedBy())
                .modifiedTimestamp(customer.getModifiedTimestamp()).build();
    }
}
