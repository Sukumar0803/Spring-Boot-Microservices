package com.microservices.customer.services;

import com.microservices.customer.dto.CustomerDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getCustomersByFilters(String id, String firstname, String lastName, String state, String mobileNumber, String city, String country, String email, String createdDateFrom, String createdDateTo, String modifiedFromDate, String modifiedToDate);
}
