package com.microservices.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(
        name = "CustomerDTO",
        description = "Schema to Customer information"
)
public class CustomerDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8565106555499020358L;

    @Schema(description = "Id of the customer")
    private String id;

    @Schema(description = "first name of the customer", example = "sukumar")
    @NotEmpty(message = "First name is mandatory")
    private String firstname;

    @Schema(description = "last name of the customer", example = "kalpam")
    @NotEmpty(message = "Last Name is mandatory")
    private String lastname;

    @Schema(description = "Email address of the customer", example = "kalpamsukumar@gmail.com")
    @Email(message = "Email format is incorrect")
    @NotEmpty(message = "Email field is Mandatory")
    private String email;

    @Schema( description = "Mobile Number of the customer", example = "9392576076" )
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @NotEmpty(message = "Mobile Number field is Mandatory ")
    private String mobileNumber;

    @Schema(description = "address of the customer")
    private AddressDTO address;

    @Schema(description = "Customer Created By", example = "SUKUMAR")
    private String createdBy;

    @Schema(description = "Time Stamp of Created", example = "2024-03-10 12:10:30")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/New_York")
    private LocalDateTime createdTimestamp;

    @Schema(description = "Customer Modified By", example = "SUKUMAR")
    private String modifiedBy;

    @Schema(description = "Time Stamp of customer Modified", example = "2024-03-10 12:10:30")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/New_York")
    private LocalDateTime modifiedTimestamp;
}
