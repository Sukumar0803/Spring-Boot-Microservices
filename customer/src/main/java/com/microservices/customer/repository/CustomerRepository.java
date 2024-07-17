package com.microservices.customer.repository;

import com.microservices.customer.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> getAllCustomers();
    Customer addCustomer(Customer customer);
    Optional<Customer> getCustomerByEmail(String email);
    List<Customer> getCustomersByFilters(String id, String firstname, String lastName, String state, String mobileNumber, String city, String country, String email, String createdDateFrom, String createdDateTo, String modifiedFromDate, String modifiedToDate);
    void batchInsertCustomers(List<Customer> customers);
}
