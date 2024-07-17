package com.microservices.customer.resources;

import com.microservices.customer.dto.CustomerDTO;
import com.microservices.customer.services.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerResourceImpl implements CustomerResource {

    private static final Logger LOGGER = LogManager.getLogger(CustomerResourceImpl.class);

    private final CustomerService customerService;

    public CustomerResourceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        LOGGER.info("getAllCustomers() >> Start()");
        List<CustomerDTO> customers = customerService.getAllCustomers();
        LOGGER.info("getAllCustomers() >> End()");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customers);
    }

    @Override
    public ResponseEntity<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
        LOGGER.info("createCustomer() >> Start()");
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        LOGGER.info("createCustomer() >> End()");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdCustomer);
    }

    /**
     * @param id           id of customer
     * @param firstname    firstname of customer
     * @param lastName     lastName of customer
     * @param state        state of customer
     * @param mobileNumber mobileNumber of customer
     * @param city         city of customer
     * @param country      country of customer
     * @param email        email of customer
     * @return ResponseEntity<List < CustomerDTO>>
     */
    @Override
    public ResponseEntity<List<CustomerDTO>> getCustomersByFilters(String id, String firstname, String lastName, String state, String mobileNumber, String city, String country, String email, String createdDateFrom,
                                                                   String createdDateTo,
                                                                   String modifiedFromDate,
                                                                   String modifiedToDate) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerService.getCustomersByFilters(id, firstname, lastName, state, mobileNumber, city, country, email, createdDateFrom,
                        createdDateTo,
                        modifiedFromDate,
                        modifiedToDate));
    }
}
