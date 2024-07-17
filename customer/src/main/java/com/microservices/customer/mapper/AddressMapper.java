package com.microservices.customer.mapper;

import com.microservices.customer.dto.AddressDTO;
import com.microservices.customer.entity.Address;

public class AddressMapper {

    public static Address toAddress(AddressDTO addressDTO) {
        return Address.builder()
                .line1(addressDTO.getLine1())
                .line2(addressDTO.getLine2())
                .state(addressDTO.getState())
                .city(addressDTO.getCity())
                .country(addressDTO.getCountry())
                .zipCode(addressDTO.getZipCode()).build();
    }

    public static AddressDTO fromAddress(Address address) {
        return AddressDTO.builder()
                .line1(address.getLine1())
                .line2(address.getLine2())
                .state(address.getState())
                .city(address.getCity())
                .country(address.getCountry())
                .zipCode(address.getZipCode()).build();
    }
}
