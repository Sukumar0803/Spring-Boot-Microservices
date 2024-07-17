package com.microservices.customer.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document("customers")
public class Customer extends BaseEntity {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String mobileNumber;
    private Address address;
}
