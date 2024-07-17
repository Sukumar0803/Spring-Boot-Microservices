package com.microservices.customer.services;

import com.microservices.customer.dto.CustomerDTO;
import com.microservices.customer.entity.Customer;
import com.microservices.customer.exception.ApplicationException;
import com.microservices.customer.exception.CustomerAlreadyExistException;
import com.microservices.customer.exception.ValidationException;
import com.microservices.customer.mapper.CustomerMapper;
import com.microservices.customer.repository.CustomerRepository;
import com.microservices.customer.utils.Constants;
import com.microservices.customer.utils.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    Logger LOGGER = LogManager.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;


    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * To Get All Customers
     *
     * @return List Of Customers
     */
    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customersFromDb = customerRepository.getAllCustomers();
        return customersFromDb.stream().map(CustomerMapper::fromCustomer).collect(Collectors.toList());
    }

    /**
     * To Create Customer
     *
     * @param customerDTO input Request
     * @return Customer Obj Saved in DB
     */
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        LOGGER.info("createCustomer() >> Start()");
        Customer customer = new Customer();
        try {
            if (!CheckCustomerAlreadyExists(customerDTO)) {
                customer = CustomerMapper.toCustomer(customerDTO);
                customer = customerRepository.addCustomer(customer);
                LOGGER.info("createCustomer() >> End()");
            }
            return CustomerMapper.fromCustomer(customer);
        } catch (CustomerAlreadyExistException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApplicationException(ex.getMessage(), "API_CUSTOMER-SERVICE_CREATE_002");
        }
    }

    /**
     * @param id               customer
     * @param firstname        customer
     * @param lastName         customer
     * @param state            customer
     * @param mobileNumber     customer
     * @param city             customer
     * @param country          customer
     * @param email            customer
     * @param createdDateFrom  customer
     * @param createdDateTo    customer
     * @param modifiedFromDate customer
     * @param modifiedToDate   customer
     * @return List of customers
     */
    @Override
    public List<CustomerDTO> getCustomersByFilters(String id, String firstname, String lastName, String state, String mobileNumber, String city, String country, String email, String createdDateFrom, String createdDateTo, String modifiedFromDate, String modifiedToDate) {
        validateInputFilters(id, firstname, lastName, state, mobileNumber, city, country, email, createdDateFrom,
                createdDateTo,
                modifiedFromDate,
                modifiedToDate);
        List<Customer> customerList = customerRepository.getCustomersByFilters(id, firstname, lastName, state, mobileNumber, city, country, email, createdDateFrom,
                createdDateTo,
                modifiedFromDate,
                modifiedToDate);
        return customerList.stream().map(CustomerMapper::fromCustomer).collect(Collectors.toList());
    }

    private void validateInputFilters(String id, String firstname, String lastName, String state, String mobileNumber, String city, String country, String email, String createdDateFrom, String createdDateTo, String modifiedFromDate, String modifiedToDate) {
        if (id == null && firstname == null && lastName == null && state == null && mobileNumber == null
                && city == null && country == null && email == null && createdDateFrom == null
                && createdDateTo == null && modifiedFromDate == null && modifiedToDate == null) {
            throw new ValidationException("At least one filter must be provided", "API-GET_CUSTOMERS_BY_FILTERS-SERVICE-001");
        }
        if (createdDateFrom != null
                && createdDateTo != null) {
            validateDateRange(createdDateFrom, createdDateTo, "Created Date");
        }

        if (modifiedFromDate != null && modifiedToDate != null) {
            validateDateRange(modifiedFromDate, modifiedToDate, "Modified Date");
        }
    }

    private void validateDateRange(String fromDate, String toDate, String fieldName) {
        if (fromDate != null && toDate != null && checkIsAfter(fromDate, toDate)) {
            throw new ValidationException(fieldName + " From Date cannot be after To Date.", "API-GET_CUSTOMERS_BY_FILTERS-SERVICE-002");
        }
    }

    private boolean checkIsAfter(String fromDate, String toDate) {
        LocalDate from = DateUtils.getLocalDateFromString(fromDate);
        LocalDate to = DateUtils.getLocalDateFromString(toDate);
        return from.isAfter(to);
    }

    /**
     * Method To check if Customer Already Present in DB
     *
     * @param customerDTO Input Customer
     * @return Boolean check if already Exists
     */
    private boolean CheckCustomerAlreadyExists(CustomerDTO customerDTO) {
        Optional<Customer> customerFromDB = customerRepository.getCustomerByEmail(customerDTO.getEmail());
        if (customerFromDB.isPresent()) {
            throw new CustomerAlreadyExistException(String.format(Constants.ErrorMessage.CUSTOMER_ALREADY_EXISTS_ERROR_MESSAGE, customerDTO.getEmail()), "API_CUSTOMER-SERVICE_CREATE_001");
        }
        return false;
    }
}
