package com.microservices.customer.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    private String line1;
    private String line2;
    private String city;
    private String state;
    private String country;
    private Integer zipCode;
}
