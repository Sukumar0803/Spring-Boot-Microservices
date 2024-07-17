package com.microservices.customer.repository;

import com.microservices.customer.entity.Customer;
import com.microservices.customer.exception.ApplicationException;
import com.microservices.customer.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    Logger LOGGER = LogManager.getLogger(CustomerRepositoryImpl.class);

    private final MongoTemplate mongoTemplate;

    public CustomerRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * @return all customers from DB
     */
    @Override
    public List<Customer> getAllCustomers() {
        try {
            return mongoTemplate.findAll(Customer.class);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new ApplicationException(e.getMessage(), "API-CUSTOMER-REPOSITORY_GETALL_001");
        }
    }

    /**
     * @param customer input Customer Obj
     * @return value after saving
     */
    @Override
    public Customer addCustomer(Customer customer) {
        try {
            return mongoTemplate.insert(customer);
        } catch (Exception e) {
            LOGGER.error(e.getStackTrace());
            throw new ApplicationException(e.getMessage(), "API-CUSTOMER-REPOSITORY_CREATE_001");
        }
    }

    /**
     * Retrieves a customer by email.
     *
     * @param email The email of the customer.
     * @return An Optional containing the customer if found, otherwise empty.
     */
    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email)); // Adjust field name here
            // Find the customer in MongoDB
            Customer customer = mongoTemplate.findOne(query, Customer.class);
            return Optional.ofNullable(customer); // Return Optional
        } catch (Exception ex) {
            LOGGER.error(ex);
            throw new ApplicationException(ex.getMessage(), "API-CUSTOMER-REPOSITORY_GET_BY_EMAIL_001");
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
    public List<Customer> getCustomersByFilters(String id, String firstname, String lastName, String state, String mobileNumber, String city, String country, String email, String createdDateFrom, String createdDateTo, String modifiedFromDate, String modifiedToDate) {
        try {
            Query query = new Query();

            if (id != null) {
                query.addCriteria(Criteria.where("id").is(id));
            }
            if (firstname != null) {
                query.addCriteria(Criteria.where("firstname").regex(firstname));
            }
            if (lastName != null) {
                query.addCriteria(Criteria.where("lastName").regex(lastName));
            }

            if (email != null) {
                query.addCriteria(Criteria.where("email").is(email));
            }

            if (state != null) {
                query.addCriteria(Criteria.where("address.state").is(state));
            }
            if (mobileNumber != null) {
                query.addCriteria(Criteria.where("mobileNumber").is(mobileNumber));
            }
            if (city != null) {
                query.addCriteria(Criteria.where("address.city").is(city));
            }

            if (country != null) {
                query.addCriteria(Criteria.where("address.country").is(country));
            }

            if (createdDateFrom != null && createdDateTo != null) {
                query.addCriteria(Criteria.where("createdDate").gte(createdDateFrom));
                query.addCriteria(Criteria.where("createdDate").lt(createdDateTo));
            }

            if (modifiedFromDate != null && modifiedToDate != null) {
                query.addCriteria(Criteria.where("modifiedDate").gte(modifiedFromDate));
                query.addCriteria(Criteria.where("modifiedDate").lt(modifiedToDate));
            }
            query.with(Sort.by(Sort.Direction.DESC, "createdTimestamp"));
            return mongoTemplate.find(query, Customer.class);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw  new ApplicationException(Constants.SystemErrorCode.SYSTEM_EXCEPTION_ERROR_CODE, "API-GET_CUSTOMERS_BY_FILTERS-REPOSITORY-001");
        }

    }

    public void batchInsertCustomers(List<Customer> customers) {
        mongoTemplate.insertAll(customers);
    }
}
