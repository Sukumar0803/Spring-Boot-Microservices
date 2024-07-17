package com.microservices.customer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        name = "AddressDTO",
        description = "Schema to Address information"
)
public class AddressDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -3061369120041518840L;

    @Schema(description = "Line1 of the address", example = "15 Line A")
    private String line1;
    @Schema(description = "Line2 of the address", example = "15 Line B Opposite")
    private String line2;
    @Schema(description = "City of the address", example = "Nellore")
    private String city;
    @Schema(description = "State of the address", example = "Andhra pradesh")
    private String state;
    @Schema(description = "Country of the address", example = "India")
    private String country;
    @Schema(description = "Zip Code of the address", example = "524346")
    private Integer zipCode;
}
